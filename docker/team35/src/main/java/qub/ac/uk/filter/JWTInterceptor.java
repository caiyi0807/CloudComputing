package qub.ac.uk.filter;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import qub.ac.uk.util.JWTUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("token");
        try {
            JWTUtils.verify(token);
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            log.info("无效签名");
            return false;
        }catch (TokenExpiredException e){
            e.printStackTrace();
            log.info("token过期");
            return false;
        }catch (AlgorithmMismatchException e){
            e.printStackTrace();
            log.info("token算法不一致");
            return false;
        }catch (Exception e){
            e.printStackTrace();
            log.info("token无效");
            return false;
        }
        return true;
    }
}


