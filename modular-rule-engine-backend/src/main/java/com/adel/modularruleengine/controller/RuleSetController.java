package com.adel.modularruleengine.controller;

import com.adel.modularruleengine.dto.RulesGroupDto;
import com.adel.modularruleengine.model.*;
import com.adel.modularruleengine.service.DaoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/ruleSet")
public class RuleSetController {

    private final DaoService daoService;

    @PostMapping
    public @ResponseBody Response alterRuleMethod(@RequestBody final RuleRequest ruleRequest){
        //validate

        final String groupName = ruleRequest.getGroupName();
        RulesGroup rulesGroup = Optional.ofNullable(daoService.getGroupName(groupName))
                            .orElse(RulesGroup.builder()
                                    .groupName(groupName)
                                    .build());
        rulesGroup = daoService.saveRule(rulesGroup);

        final RuleAttributes ruleAttributes = ruleRequest.getRuleAttributes();

        final RulesMethod.RulesMethodBuilder rulesMethodBuilder = RulesMethod.builder()
                .rulesGroup(rulesGroup)
                .methodName(ruleAttributes.getRuleMethodName())
                .dateTimeRegistered(LocalDateTime.now())
                .ruleDescription(ruleAttributes.getRuleDescription())
                .parameters(ruleAttributes.getParameters())
                .whereSet(ruleAttributes.getWhere())
                .ruleName(ruleAttributes.getRuleName())
                .statement(ruleAttributes.getStatement());

        RulesMethod rulesMethod = rulesGroup.getMethods().stream()
                .filter(f->f.getMethodName().equals(ruleAttributes.getRuleMethodName()))
                .findFirst()
                .map(m->rulesMethodBuilder
                        .id(m.getId())
                        .build())
                .orElse(rulesMethodBuilder.build());
        rulesMethod = daoService.saveMethod(rulesMethod);

        if(null== rulesMethod.getId()){
            return new Response(HttpStatus.NOT_FOUND.name(), "Rule updated fail", new HashMap<>());
        }

        return new Response(HttpStatus.CREATED.name(), "Rule updated success", new HashMap<>());
    }

    @GetMapping
    public @ResponseBody Page<RulesGroupDto> getAllRuleGroup(Integer page,
                                                             Integer size){
        page = Optional.ofNullable(page).orElse(0);
        size = Optional.ofNullable(size).orElse(15);
        return daoService.getAllRuleGroup(page, size);
    }

}
