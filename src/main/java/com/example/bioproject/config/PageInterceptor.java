package com.example.bioproject.config;

import com.example.bioproject.dtos.LoginResponse;
import com.example.bioproject.enums.EmployeeRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

public class PageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws IOException {
        LoginResponse user = (LoginResponse) request.getSession().getAttribute("user");

        if(user == null){
            response.sendRedirect("/index.html");
            return false;
        }
        if(user.role() != EmployeeRole.FILMOPERATOER){
            response.sendRedirect("/denied.html");
            return false;
        }
        return true;
    }

}
