package com.whut.smart.support.shiro;

import com.whut.smart.context.UserContextHolder;
import com.whut.smart.dto.ResultDto;
import com.whut.smart.dto.UserDto;
import com.whut.smart.service.AccessTokenService;
import com.whut.smart.service.UserService;
import com.whut.smart.service.impl.AccessTokenServiceImpl;
import com.whut.smart.service.impl.UserServiceImpl;
import com.whut.smart.util.CheckNull;
import com.whut.smart.util.JsonUtil;
import com.whut.smart.util.ResponseWriter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自己实现access_token，登录成功后返回access_token给vue js
 * 然后vue js每次请求携带access_token，然后判断用户是否有权限访问
 *
 * Created by null on 2017/1/2.
 */
public class DefaultAccessTokenAuthorizationFilter extends AuthorizationFilter {

    private static final Logger log = LoggerFactory.getLogger(DefaultAccessTokenAuthorizationFilter.class);

    @Resource(type = AccessTokenServiceImpl.class)
    private AccessTokenService accessTokenService;

    @Resource(type = UserServiceImpl.class)
    private UserService userService;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String access_token = httpServletRequest.getParameter("access_token");

        // 没有access_token或失效了
        ResultDto<String> resultDto = new ResultDto<>();
        if (!accessTokenService.checkAccessToken(access_token)) {
            resultDto.setCode(HttpStatus.UNAUTHORIZED.value());
            resultDto.setMessage("不正确的access_token");
            resultDto.setResult("请进行授权");
            // 发送json
            new ResponseWriter(httpServletResponse).writerJson(JsonUtil.toJson(resultDto));
            return false;
        }

        // 判断该授权用户是否具备该角色
        String username = accessTokenService.getUsername(access_token);
        UserDto userDto = userService.getByUsername(username);
        // 用户不存在
        if (CheckNull.isNull(userDto)) {
            resultDto.setCode(HttpStatus.BAD_REQUEST.value());
            resultDto.setMessage("授权用户被删除了");
            // 发送json
            new ResponseWriter(httpServletResponse).writerJson(JsonUtil.toJson(resultDto));
            return false;
        }

        // 将用户信息放入上下文
        UserContextHolder.set(userDto);

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
        resultDto.setCode(HttpStatus.FORBIDDEN.value());
        resultDto.setMessage("访问被拒绝");
        resultDto.setResult("权限不足");
        // 发送json
        new ResponseWriter(httpServletResponse).writerJson(JsonUtil.toJson(resultDto));
        return false;
    }

}
