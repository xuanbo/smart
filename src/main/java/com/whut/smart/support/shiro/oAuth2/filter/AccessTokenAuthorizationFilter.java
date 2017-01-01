package com.whut.smart.support.shiro.oAuth2.filter;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.dto.UserDto;
import com.whut.smart.service.UserService;
import com.whut.smart.support.shiro.oAuth2.service.OAuthService;
import com.whut.smart.util.CheckNull;
import com.whut.smart.util.JsonUtil;
import com.whut.smart.util.ResponseWriter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义需要携带access_token的资源认证filter
 * 携带了有效的access_token并且该授权用户拥有访问的资源的角色则允许访问
 *
 * Created by null on 2017/1/1.
 */
public class AccessTokenAuthorizationFilter extends AuthorizationFilter {

    private static final Logger log = LoggerFactory.getLogger(AccessTokenAuthorizationFilter.class);

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String access_token = httpServletRequest.getParameter("access_token");
        // 没有access_token或失效了
        if (!oAuthService.checkAccessToken(access_token)) {
            ResultDto<String> resultDto = new ResultDto<>();
            resultDto.setCode(HttpStatus.UNAUTHORIZED.value());
            resultDto.setMessage("不正确的access_token");
            resultDto.setResult("请进行授权");
            // 发送json
            new ResponseWriter(httpServletResponse).writerJson(JsonUtil.toJson(resultDto));
            return false;
        }
        // 判断该授权用户是否具备该角色
        String username = oAuthService.getUsernameByAccessToken(access_token);
        UserDto userDto = userService.getByUsername(username);
        // 访问需要的角色
        String[] roles = (String[]) mappedValue;
        // 为null则不需要认证
        if (CheckNull.isNull(roles)) {
            return true;
        }
        for (String role : roles) {
            // 拥有一个角色则允许访问
            if (role.endsWith(userDto.getRoleDto().getName())) {
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
