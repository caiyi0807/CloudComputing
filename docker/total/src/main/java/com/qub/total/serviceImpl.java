package com.qub.total;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class serviceImpl implements service {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public List<Total> find() {
        return mongoTemplate.findAll(Total.class, "TotalFunction");
    }

}
