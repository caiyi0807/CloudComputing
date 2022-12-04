package com.qub.total;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;


@RestController
@SpringBootApplication
public class TotalApplication {
    public static void main(String[] args) {
        SpringApplication.run(TotalApplication.class, args);
    }
    @RequestMapping("/")
    public Total getTotal(@RequestParam(value = "module_1", defaultValue = "") String module_1, @RequestParam(value = "mark_1", defaultValue = "") String mark_1,
                          @RequestParam(value = "module_2", defaultValue = "") String module_2, @RequestParam(value = "mark_2", defaultValue = "") String mark_2,
                          @RequestParam(value = "module_3", defaultValue = "") String module_3, @RequestParam(value = "mark_3", defaultValue = "") String mark_3,
                          @RequestParam(value = "module_4", defaultValue = "") String module_4, @RequestParam(value = "mark_4", defaultValue = "") String mark_4,
                          @RequestParam(value = "module_5", defaultValue = "") String module_5, @RequestParam(value = "mark_5", defaultValue = "") String mark_5,
                          HttpServletResponse response
    ) throws IOException {
        response.addHeader("Access-Control-Allow-Origin", "*");
        String[] marks = {mark_1, mark_2, mark_3, mark_4, mark_5};
        String[] modules = {module_1, module_2, module_3, module_4, module_5};
        boolean error = false;
        ArrayList<String> errorInformation = new ArrayList<>();
        for (int i = 0; i < marks.length; i++) {
            marks[i] = marks[i].trim();
            if (marks[i].contentEquals("")) {
                error = true;
                int j = i + 1;
                String s = "Please input value for mark_" + j + ".";
                errorInformation.add(s);
                continue;
            }
            if (!isNumeric(marks[i])) {
                error = true;
                marks[i] = "";
                int j = i + 1;
                String s = "Please input integer for mark_" + j + " and it should be greater than 0. ";
                errorInformation.add(s);
                continue;
            }
            if (Integer.parseInt(marks[i]) > 100) {
                error = true;
                marks[i] = "";
                int j = i + 1;
                String s = "Mark_" + j + " should be less than 100. ";
                errorInformation.add(s);
            }
        }
        for (int i = 0; i < modules.length; i++) {
            modules[i] = modules[i].trim();
            if (modules[i].contentEquals("")) {
                error = true;
                int j = i + 1;
                String s = "Please input the name of module_" + j + ".";
                errorInformation.add(s);
            }
        }
        Total total = new Total(marks, modules, error, errorInformation, 0);
        total.total(marks);
        writeDataToFile(total.toString()+'\n');
        return total;
    }
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0; ) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public void writeDataToFile(String s) throws IOException {
        FileOutputStream o = null;
        String path = "1.txt";
        byte[] buff = new byte[]{};
        try {
            File file = new File(path + path);
            if (!file.exists()) {
                file.createNewFile();
            }
            buff = s.getBytes();
            o = new FileOutputStream(file, true);
            o.write(buff);
            o.flush();
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/history")
    public void history(HttpServletResponse response) throws IOException {
        String filePath = "1.txt";
        System.out.println("filePath:" + filePath);
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] bs = new byte[1024];
        int len = 0;
        response.reset();
        URL u = new URL("file:///" + filePath);
        String contentType = u.openConnection().getContentType();
        response.setContentType(contentType);
        OutputStream out = response.getOutputStream();
        while ((len = br.read(bs)) > 0) {
            out.write(bs, 0, len);
        }
        out.flush();
        out.close();
        br.close();
    }

    @RequestMapping("/clear")
    public void clear(){
        FileOutputStream o = null;
        String path = "";
        String filename = "1.txt";
        byte[] buff = new byte[]{};
        try {
            File file = new File(path + filename);
            if (!file.exists()) {
                file.createNewFile();
            }
            buff = " ".getBytes();
            o = new FileOutputStream(file, false);
            o.write(buff);
            o.flush();
            o.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

