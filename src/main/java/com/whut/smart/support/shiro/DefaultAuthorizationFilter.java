package com.whut.smart.support.shiro;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.util.CheckNull;
import com.whut.smart.util.JsonUtil;
import com.whut.smart.util.ResponseWriter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * 自定义认证filter，认证失败返回json信息
 *
 * Created by null on 2017/1/1.
 */
public class DefaultAuthorizationFilter extends AuthorizationFilter {

    private static final Logger log = LoggerFactory.getLogger(DefaultAuthorizationFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        log.debug("请求uri => {}, remoteAddress => {}", uri, httpServletRequest.getRemoteAddr());
        Subject subject = getSubject(request, response);
        // 没有认证
        if (!subject.isAuthenticated()) {
            ResultDto<String> resultDto = new ResultDto<>();
            resultDto.setCode(HttpStatus.UNAUTHORIZED.value());
            resultDto.setMessage("没有认证");
            resultDto.setResult("请进行登录授权");
            // 发送json
            new ResponseWriter(httpServletResponse).writerJson(JsonUtil.toJson(resultDto));
            return true;
        }
        // 访问需要的角色
        String[] roles = (String[]) mappedValue;
        log.debug("访问需要的角色 => {}", Arrays.toString(roles));
        // 为null则不需要认证
        if (CheckNull.isNull(roles)) {
            return true;
        }
        for (String role : roles) {
            // 拥有一个角色则允许访问
            if (subject.hasRole(role)) {
                log.debug("拥有该角色 => {}", role);
                return true;
            }
        }
        log.debug("不具备角色，访问被拒绝");
        ResultDto<String> resultDto = new ResultDto<>();
        resultDto.setCode(HttpStatus.FORBIDDEN.value());
        resultDto.setMessage("访问被拒绝");
        resultDto.setResult("权限不足");
        // 发送json
        new ResponseWriter(httpServletResponse).writerJson(JsonUtil.toJson(resultDto));
        return false;
    }

}
