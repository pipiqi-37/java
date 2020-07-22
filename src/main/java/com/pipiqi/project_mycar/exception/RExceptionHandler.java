package com.pipiqi.project_mycar.exception;

import com.pipiqi.project_mycar.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class RExceptionHandler {
    /**
     *
     * 自定义全局异常类
     */

    @ExceptionHandler(RException.class)
    @ResponseBody
    public R rExc(RException rException) {
        R r = new R();
        r.put("code", 500);
        r.put("msg", rException.getMessage());
        return r;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R allExc(Exception exception) {
        R r = new R();
        r.put("code", 500);
        r.put("msg", "系统内部错误！");
        return r;
    }

    @ExceptionHandler(AuthorizationException.class)
    public String authorExce(AuthorizationException authorizationException) {
        return "redirect:unauthorized.html";
    }


}
