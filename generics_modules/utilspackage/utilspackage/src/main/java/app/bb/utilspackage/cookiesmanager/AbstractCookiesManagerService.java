package app.bb.utilspackage.cookiesmanager;


import ch.qos.logback.core.util.Duration;
import lombok.Getter;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.ResponseCookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class AbstractCookiesManagerService {
    protected final long COOKIES_MAX_AGE = 100000;


    private List<ResponseCookie> cookies = new ArrayList<>();

    public void addCookie(String nome,String value){
        ResponseCookie resp = ResponseCookie
                .from(nome)
                .value(value)
                .maxAge(COOKIES_MAX_AGE)
                .secure(true)
                .build();
        cookies.add(resp);
    }

    public void updateCookie(String nome,String value){
        cookies.forEach(
                c->{
                    if(nome.equals(c.getName())){
                        c.mutate().value(value);
                    }
                }
        );
    }

    public void deleteCookie(String nome){
        cookies.stream().forEach(
                c->{
                    if(c.getName().equals(nome)){
                        c.mutate().maxAge(0);
                    }
                }
        );

    }

    public void clearAllCookies(){
        cookies.stream().forEach(
                c-> {
                    c.mutate().maxAge(0);
                }
        );
        cookies.clear();
    }

    public ResponseCookie getCookie(String name){
        return cookies.stream().filter(c->name.equals(c.getName())).findFirst().orElse(null);
    }

    public List<ResponseCookie> getAllCookies(){
        return cookies;
    }


}
