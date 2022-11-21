package qub.ac.uk.model;

import lombok.Data;
//添加 @Data 注解可以自动生成getter和setter方法
//此注解包含在lombok包内
@Data
public class User{
    private String username;
    private String _id;
    private String password;
    private String token;//后端直接将token加入到实体类模型中
}

