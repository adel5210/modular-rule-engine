package com.adel.modularruleengine.service;

import com.adel.modularruleengine.config.PropertyConfig;
import com.adel.modularruleengine.model.RuleAttributes;
import com.adel.modularruleengine.utility.ResourceUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.adel.modularruleengine.model.RuleAttributeType.*;

@Service
@AllArgsConstructor
@Slf4j
public class FileRuleService {

    private final PropertyConfig propertyConfig;

    public void writeToRule(RuleAttributes ruleAttributes, String ruleFile) throws IOException {
        ruleAttributes.set_dateRegistered(LocalDateTime.now().toString());

        final List<String> codeLines = ResourceUtil.readAllLine(propertyConfig.getRuleTemplate());

        final Map<String, Object> fields = ResourceUtil.getFieldsAndValues(ruleAttributes);

        final List<String> copyCodeLine = new ArrayList<>();
        final List<String> portionCode = new ArrayList<>();
        boolean isPortionCode = false;

        final String startMethod = START_METHOD.getProperty();
        final String endMethod = END_METHOD.getProperty();
        final String next = NEXT.getProperty();

        for (String line : codeLines) {
            final String originalLine = line;

            final boolean atEdgeMethod = line.contains(startMethod) || line.contains(endMethod);

            for (final Map.Entry<String, Object> field : fields.entrySet()) {

                if (atEdgeMethod) {
                    isPortionCode = !isPortionCode;
                    break;
                }

                final String wrapField = getWrap(field.getKey());
                if (line.contains(wrapField) ) {
                    line = line.replace(wrapField, field.getValue().toString());
                }
            }

            if (isPortionCode) {
                if(!atEdgeMethod) portionCode.add(line);
                copyCodeLine.add(originalLine);
            } else {
                copyCodeLine.add(line);
            }
        }

        final List<String> newCodeLine = new ArrayList<>();
        for (String copyLine : copyCodeLine) {
            if (copyLine.contains(next)) {
                newCodeLine.addAll(portionCode);
                newCodeLine.add("\t"+"// "+next);
            }else {
                newCodeLine.add(copyLine);
            }
        }

        ResourceUtil.writeAllLines(newCodeLine, ruleFile);


    }

}
