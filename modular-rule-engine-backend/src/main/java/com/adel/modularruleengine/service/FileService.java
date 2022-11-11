package com.adel.modularruleengine.service;

import com.adel.modularruleengine.dto.RuleAttributes;
import com.adel.modularruleengine.utility.ResourceUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.util.FileUtil;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
@Slf4j
public class FileService {

    public void addNewRule(RuleAttributes ruleAttributes) throws IOException {
        ruleAttributes.set_dateRegistered(LocalDateTime.now().toString());

        final List<String> codeLines = ResourceUtil.readAllLine("/home/pc7/IdeaProjects/modular-rule-engine/modular-rule-engine-backend/src/main/resources/ruleTemplate.java");

        final Map<String, Object> fields = ResourceUtil.getFieldsAndValues(ruleAttributes);

        final List<String> copyCodeLine = new ArrayList<>();
        final List<String> portionCode = new ArrayList<>();
        boolean isPortionCode = false;

        final String startMethod = "@{_startMethod}";
        final String endMethod = "@{_endMethod}";
        final String next = "@{_next}";

        for (String line : codeLines) {
            final String originalLine = line;

            final boolean atEdgeMethod = line.contains(startMethod) || line.contains(endMethod);

            for (final Map.Entry<String, Object> field : fields.entrySet()) {

                if (atEdgeMethod) {
                    isPortionCode = !isPortionCode;
                    break;
                }

                final String wrapField = "@{" + field.getKey() + "}";
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

        ResourceUtil.writeAllLines(newCodeLine, "/home/pc7/IdeaProjects/modular-rule-engine/modular-rule-engine-backend/src/main/resources/rule2.java");


    }

}
