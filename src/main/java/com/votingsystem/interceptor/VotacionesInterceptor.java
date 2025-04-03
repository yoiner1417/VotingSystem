package com.votingsystem.interceptor;

import com.votingsystem.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class VotacionesInterceptor implements HandlerInterceptor, InterceptorVotaciones {

    @Autowired
    private VoteService voteService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().startsWith("/votaciones") && !voteService.estanVotacionesAbiertas()) {
            response.sendRedirect("/votaciones-cerradas");
            return false;
        }
        return true;
    }

}