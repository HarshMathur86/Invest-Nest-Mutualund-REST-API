package com.investnest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    public void test() {
        System.out.println("test service");
        log.info("testService");
    }

}
