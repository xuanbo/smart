package com.whut.smart.support.shiro;

import com.whut.smart.dto.UserDto;
import com.whut.smart.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 自定义Realm
 *
 * Created by null on 2016/12/31.
 */
public class DefaultRealm extends AuthorizingRealm {

    private static final Logger log = LoggerFactory.getLogger(DefaultRealm.class);

    @Resource
    private UserService userService;

    /*
     * 访问授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /*
     * 身份认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户名
        String username = (String) token.getPrincipal();
        UserDto userDto = userService.getByUsername(username);
        log.debug("{}", userDto);
        if (userDto == null) {
            throw new UnknownAccountException("账号不存在");
        }
        return new SimpleAuthenticationInfo(username, userDto.getPassword(), getName());
    }
}
