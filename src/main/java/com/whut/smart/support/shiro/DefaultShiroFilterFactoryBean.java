package com.whut.smart.support.shiro;

import com.whut.smart.service.ResourceService;
import com.whut.smart.util.CheckNull;
import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 从数据库中读取FilterChains
 * 首先在配置文件中读取默认配置：
 *      (1)不需要过滤的FilterChains，/assets/** = anon
 *      (2)Shiro自带的需要认证的FilterChains，例如/logout = logout
 *
 * 然后在数据库中配置自定义的filter需要授权的url和角色信息，也就是DefaultAuthorizationFilter拦截的url
 * /admin/xxx = needAnyRole['ROLE_ADMIN']，其中needAnyRole为配置的DefaultAuthorizationFilter的别名
 *
 * 因此就是构造出/admin/xxx = needAnyRole['ROLE_ADMIN']这样的形式传递给Shiro就好了
 *
 * 你可以自己去细化角色的权限，例如perms['ROLE_USER:add']的样式，对于一些erp等权限细的系统需要去在此类的基础上去扩展
 *
 * 对于restful url需要说明的是：可能我们有时需要做这样一个需求，都可以查看id为1的报表/report/1，但是只有管理员可以去删除，
 * 这样需要去对url的操作状态去细化，那么可以在授权filter中去过滤请求方式，如DELETE请求，然后判断是否具备DELETE权限，可以
 * 将权限定义为perms['ROLE_USER:DELETE']，判断XX:YY中YY是否为DELETE就可以了。
 *
 * 其实一般的项目中用url和角色限制就好了。
 *
 * Created by null on 2017/1/2.
 */
public class DefaultShiroFilterFactoryBean extends ShiroFilterFactoryBean {

    private static final Logger log = LoggerFactory.getLogger(DefaultShiroFilterFactoryBean.class);

    @Autowired
    private ResourceService resourceService;

    // 配置文件中定义的filterChains
    private static String defaultDefinitions;

    // 自定义拦截的Filter的别名，就是/admin/xxx = needAnyRole['ROLE_ADMIN']中的needAnyRole
    private String filterAlias;

    public void setFilterAlias(String filterAlias) {
        this.filterAlias = filterAlias;
    }

    /*
     * 初始化设置过滤链
     */
    @Override
    public void setFilterChainDefinitions(String definitions) {
        log.debug("读取配置文件中默认的filterChains => {}", definitions);
        // 保存配置文件中定义的filterChains
        defaultDefinitions = definitions;
        Map<String, String> urlRolesMatcher = resourceService.getUrlRolesMatcher();
        // 从数据中的信息构建FilterChainDefinitions
        final Map<String, String> databaseFilterChainDefinitions = new HashMap<>();
        log.debug("读取数据库中的filterChains");
        urlRolesMatcher.forEach((url, roles) -> {
            log.debug("url => {} roles => {}", url, roles);
            if (StringUtils.isEmpty(roles)) {
                databaseFilterChainDefinitions.put(url, filterAlias);
            } else {
                databaseFilterChainDefinitions.put(url, filterAlias + "[" + roles + "]");
            }
        });
        //加载配置默认的过滤链
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        // 加上数据库中过滤链
        section.putAll(databaseFilterChainDefinitions);
        // 让父类回调
        setFilterChainDefinitionMap(section);
    }
}
