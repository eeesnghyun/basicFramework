package com.app.basic.common.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if (exception instanceof BadCredentialsException) {
            //패스워드가 틀린 경우
            response.sendRedirect("/login?error=invalid");
        } else if (exception instanceof UsernameNotFoundException) {
            //계정이 존재하지 않는 경우
            response.sendRedirect("/login?error=account");
        } else if (exception instanceof LockedException) {
            response.sendRedirect("/login?error=lock");
        } else {
            response.sendRedirect("/login?error=invalid");
        }
    }

}