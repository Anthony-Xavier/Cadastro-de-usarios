package xavier.app.Login.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

public class CookieService {

    public static void setCookie(HttpServletResponse response, String key, String valor, int segundos) throws UnsupportedEncodingException {
        Cookie cookie = new Cookie(key, URLEncoder.encode(valor, "UTF-8"));
        cookie.setMaxAge(segundos);
        response.addCookie(cookie);
    }

    public static String getCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        String valor = Optional.ofNullable(request.getCookies())
                .map(Arrays::stream)
                .orElseGet(Stream::empty)
                .filter(cookie -> key.equals(cookie.getName()))
                .map(Cookie::getValue)
                .findAny()
                .orElse(null);

        if(valor != null){
            valor = URLDecoder.decode(valor,"UTF-8");
            return valor;
        }

        return valor;
    }
}
