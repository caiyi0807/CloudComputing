package qub.ac.uk.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import qub.ac.uk.model.User;

import java.util.Calendar;

import static sun.security.x509.X509CertImpl.SIGNATURE;

public class JWTUtils {
    public static String getToken(User user){

        Calendar calendar = Calendar.getInstance();//Calender为一个日期操作的类

        calendar.add(Calendar.DATE,1);//设置过期时间

        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("_id",user.get_id())
                .withClaim("username",user.getUsername());//写入数据
        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SIGNATURE));//生成token
        return token;
    }
    public static DecodedJWT verify(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGNATURE)).build().verify(token);
        return verify;
    }
}