package com.adel.modularruleengine.controller;

import com.adel.modularruleengine.dto.RuleAttributes;
import com.adel.modularruleengine.service.FileService;
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
@RequestMapping("/api/v1/file")
public class FileInterpreterController {

    private final FileService fileService;

    @PostMapping
    public void addRule(@RequestBody RuleAttributes ruleAttributes) throws IOException {
        fileService.addNewRule(ruleAttributes);
    }

    public void editRule(){

    }

}
