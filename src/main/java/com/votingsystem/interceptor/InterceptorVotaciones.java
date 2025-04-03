package com.votingsystem.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface InterceptorVotaciones {
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
    // Eliminar el segundo método que usa javax.servlet
}