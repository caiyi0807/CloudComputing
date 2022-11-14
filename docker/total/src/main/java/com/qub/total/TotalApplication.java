package com.qub.total;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SpringBootApplication
@RestController
public class TotalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TotalApplication.class, args);
    }


    @RequestMapping(value = {"/"}, method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Total getTotal(@RequestParam(value = "module_1", defaultValue = "") String module_1, @RequestParam(value = "mark_1", defaultValue = "") String mark_1,
                          @RequestParam(value = "module_2", defaultValue = "") String module_2, @RequestParam(value = "mark_2", defaultValue = "") String mark_2,
                          @RequestParam(value = "module_3", defaultValue = "") String module_3, @RequestParam(value = "mark_3", defaultValue = "") String mark_3,
                          @RequestParam(value = "module_4", defaultValue = "") String module_4, @RequestParam(value = "mark_4",defaultValue = "") String mark_4,
                          @RequestParam(value = "module_5", defaultValue = "") String module_5, @RequestParam(value = "mark_5", defaultValue = "") String mark_5,
                          HttpServletResponse response
    ) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        String[] marks = new String[5];
        String[] modules = new String[5];
        marks[0] = mark_1;
        marks[1] = mark_2;
        marks[2] = mark_3;
        marks[3] = mark_4;
        marks[4] = mark_5;
        modules[0] = module_1;
        modules[1] = module_2;
        modules[2] = module_3;
        modules[3] = module_4;
        modules[4] = module_5;
        Total total = new Total(marks, modules);
        total.total(marks);
        return total;
    }
}

