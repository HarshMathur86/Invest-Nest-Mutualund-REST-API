package com.investnest.controller;

import com.investnest.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping(value = "investnest/test", produces="application/json")
    public ResponseEntity<?> test() {
        testService.test();
        System.out.println("testController");
        return new ResponseEntity<>("OK test completed", HttpStatus.OK);
    }



}
