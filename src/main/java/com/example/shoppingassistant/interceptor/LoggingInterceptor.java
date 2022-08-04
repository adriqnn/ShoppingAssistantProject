package com.example.shoppingassistant.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class LoggingInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        File logFile = new File("src/main/resources/log/requests_log.txt");
        FileWriter logFileWriter = new FileWriter(logFile, true);
        PrintWriter logPrintWriter = new PrintWriter(logFileWriter);

        logPrintWriter.println("Request:");
        logPrintWriter.println(request.getRequestURL() + " " + request.getMethod());
        logPrintWriter.close();

        return true;
    }
}
