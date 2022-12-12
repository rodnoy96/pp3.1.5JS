package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@Component
public class SuccessUserHandler implements AuthenticationSuccessHandler {

    //метод опредеяет на какой url перекинет пользователя при входе, в заисимости от роли
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {

        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/main");
        } else if (authorities.contains("ROLE_USER")) {
            httpServletResponse.sendRedirect("/main");
        } else {
            httpServletResponse.sendRedirect("/");
        }
    }
}