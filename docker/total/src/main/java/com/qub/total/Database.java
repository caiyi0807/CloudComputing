package com.qub.total;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class Database {
    @Autowired
    serviceImpl service;
    @RequestMapping(value = {"/query"}, method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Total> verify() {
        return service.find();
    }
}

//./mvnw spring-boot:run