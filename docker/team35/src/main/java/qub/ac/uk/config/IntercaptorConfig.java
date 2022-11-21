package qub.ac.uk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import qub.ac.uk.filter.JWTInterceptor;

@Configuration
public class IntercaptorConfig implements WebMvcConfigurer {

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor((new JWTInterceptor()))
                .addPathPatterns("/**")//拦截所有api
                .excludePathPatterns("/login");//放行登录目录下的请求
    }
}


