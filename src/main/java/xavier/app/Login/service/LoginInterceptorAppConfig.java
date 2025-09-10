package xavier.app.Login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@Configuration
public class LoginInterceptorAppConfig {

    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor((HandlerInterceptor) loginInterceptor).excludePathPatterns(
                "/login",
                "/logar",
                "/error",
                "/cadastro"
        );
    }
}
