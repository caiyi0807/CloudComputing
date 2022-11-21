package qub.ac.uk.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qub.ac.uk.model.Msg;
import qub.ac.uk.model.User;
import qub.ac.uk.service.impl.UserServiceImpl;
import qub.ac.uk.util.JWTUtils;

@Slf4j
@RestController
public class UserController {
    @Autowired
    UserServiceImpl service;

    @PostMapping("/login")
    public Msg<User> login(@RequestBody User user) {

        if (user != null) log.info("后端成功接收User对象" + user);

        User o = service.findUserById(user.get_id());

        if (o == null) {
            log.info("用户名不存在");
            return Msg.error("用户不存在");
        } else {
            if (o.getPassword().equals(user.getPassword())) {
                log.info("查询成功" + Msg.success(o));
                o.setToken(JWTUtils.getToken(o));
                o.setPassword(null);
                return Msg.success(o);
            } else {
                log.info("密码错误,接受到的密码为：" + user.getPassword() + ";正确密码为：" + o.getPassword());
                return Msg.error("密码错误");
            }
        }
    }

    @PostMapping("/verify")
    public String verify() {
        return "验证成功";
    }
}

