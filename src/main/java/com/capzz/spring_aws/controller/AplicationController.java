package com.capzz.spring_aws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class AplicationController {

    public static final Logger log = LoggerFactory.getLogger(AplicationController.class);

    @GetMapping("/{name}")
    public ResponseEntity<?> testGetName(@PathVariable String name){
        log.info("Test controller: name: {}", name);

        return ResponseEntity.ok("Name: " + name);
    }

    @GetMapping("/{name}/wrong")
    public ResponseEntity<?> testGetNameWrong(@PathVariable String name){
        log.info("Test controller: name: {}", name);

        return ResponseEntity.ok("Name: Jobson");
    }

}
