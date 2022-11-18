package com.adel.modularruleengine.controller;

import com.adel.modularruleengine.model.RuleAttributes;
import com.adel.modularruleengine.service.DaoService;
import com.adel.modularruleengine.service.FileRuleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/ruleInterpret")
public class RuleInterpretController {

    private final FileRuleService fileRuleService;
    private final DaoService daoService;

    @PostMapping
    public void writeToRule(@RequestBody RuleAttributes ruleAttributes) throws IOException {
        fileRuleService.writeToRule(ruleAttributes, "/home/pc7/IdeaProjects/modular-rule-engine/modular-rule-engine-backend/src/main/resources/rule2.java");
    }

}
