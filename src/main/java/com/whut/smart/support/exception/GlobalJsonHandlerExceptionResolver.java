package com.whut.smart.support.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理，返回json
 * resolve exceptions thrown during handler mapping or execution
 *
 * Created by null on 2016/12/30.
 */
public class GlobalJsonHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalJsonHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.debug("GlobalJsonHandlerExceptionResolver处理异常");
        try {
            response.setContentType("application/json");
            PrintWriter print = response.getWriter();
            int code = response.getStatus();
            print.append("{\"code\": " + code + ", \"message\" : " + ex.getMessage() + "}");
            print.flush();
            print.close();
            return null;
        } catch (IOException e) {
            log.error("GlobalJsonHandlerExceptionResolver处理异常出现错误", e);
        }
        return null;
    }

}
