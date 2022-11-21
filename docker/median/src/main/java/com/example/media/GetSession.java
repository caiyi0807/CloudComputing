package com.example.media;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class GetSession {
//    @Autowired
//    MongoTemplate mongoTemplate;
    @Autowired
    HttpServletRequest request;

    @RequestMapping("/getSession")
    public Media test() {
        Media media = (Media) request.getSession().getAttribute("media");
        //List<Media>
        //mongoTemplate.findAll(Media.class, "TotalFunction");
        return media;
    }

}
