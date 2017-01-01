package com.whut.smart.support.shiro;

import com.whut.smart.config.Constants;
import com.whut.smart.dto.RoleDto;
import com.whut.smart.dto.UserDto;
import com.whut.smart.service.UserService;
import com.whut.smart.util.CheckNull;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * 自定义Realm
 * 用户跟角色一对一关系，没有细化角色拥有的权限
 *
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
        // 获取用户名
        String username = (String)principals.getPrimaryPrincipal();
        UserDto userDto = userService.getByUsername(username);
        RoleDto roleDto = userDto.getRoleDto();
        if (CheckNull.isNull(roleDto)) {
            return null;
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(roleDto.getName());
        return authorizationInfo;
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
        if (userDto.getLocked()) {
            throw new LockedAccountException("账号被锁定");
        }
        return new SimpleAuthenticationInfo(username, userDto.getPassword(), getCredentialsSalt(username), getName());
    }

    /**
     * 加密salt，这里是username + salt
     *
     * @param username 用户名
     * @return username + salt
     */
    private ByteSource getCredentialsSalt(String username) {
        return ByteSource.Util.bytes(username + Constants.SALT);
    }
}
