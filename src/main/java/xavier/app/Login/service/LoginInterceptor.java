package xavier.app.Login.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class LoginInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hamdler) throws IOException {
        if(CookieService.getCookie(request,"userName") != null){
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
