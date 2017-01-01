#   Smart

本来是想做前后端完全分离，后台只提供restful服务，前台使用vuejs + webpack构建（[front项目](http://www.github.com/xuanbo/front)）。

后来发现后台仅仅提供restful服务的话，如何进行认证呢，有些资源其实也是希望认证的。然后就想在登录后后台返回token，前台项目存储。可是仔细想想，是在前台提供登录，然后调用后台api认证呢，还是直接到后台登录，后台回调url到前台，返回token。然后我就跑偏了，后台提供oauth2，授权给前台项目。。

现在做一个总结，毕竟这两天被自己蠢哭了。

##  1如何实现OAuth2

OAuth2是什么？不了解的童鞋请百度廖雪峰的博客。

我自己写肯定搞不出来，那就直接用别人的。目前可以使用Spring Security OAuth2。Spring全家桶还是强，包君满意。可能是我智商不够，感觉Spring Security晦涩难懂，果断选择了apache oltu oauth2，用Shiro做权限。可以去参考开涛的博客。

目前，简单实现了OAuth2的服务端，一些异常没有细致的处理。

## 2运行测试

需要的童鞋直接clone下来，然后用gradle build下，下载一些依赖。然后配置数据库，在table文件夹下，schema.sql是表结构、data.sql是数据。请配置tomcat的上下文为`/`，我没有在jsp的url中加上下文。

OAuth2测试过程如下：
```
浏览器中输入
http://localhost:8080/authorize?client_id=c1ebe466-1cdc-4bd3-ab69-77c3561b9dee&response_type=code&redirect_uri=http://localhost:9999/

redirect_uri在实际中是网站的一个url，client_id为OAuth2服务器分配给你的id，有的叫appId，之后资源服务器会请求转发到一个授权登录的页面，这个时候是实际的用户输入他的账号和密码(这里输入admin123，admin123，数数据库中加密了的)，通过认证服务端就会重定向到三方客户端http://localhost:9999code=2bb787cf906ef6cd812f257c6ba554c6

拿到这个code后再模拟post提交到资源服务器获取accesstoken，以后访问就拿着这个token。这就相当于三方客户端在用户的授权下获取资源服务器的资源，却不知道密码，这就是最骚的。当然OAuth2也有拿着用户的账号和密码去请求资源的类型，一般这样都是资源服务器官方的实现。

现在用浏览器进行post请求获取accesstoken，输入
http://localhost:8080/accessToken?client_id=c1ebe466-1cdc-4bd3-ab69-77c3561b9dee&client_secret=d8346ea2-6017-43ed-ad68-19c0f971738b&grant_type=authorization_code&code=5cd548df87e43eec54682f2d2bcd0e30&redirect_uri=http://localhost:9999/
获取accesstoken，code为上一步中获取的

最后拿着accesstoken进行资源服务器的资源访问吧
我目前就写了一个获取当前用户的api，打开一个新的浏览器，输入http://localhost:8080/userInfo?access_token=d9cb3f516161c5d3ca9a4bdcafbc6a06
token为实际的，如果操作都没问题就可以看到用户名了。
```

## 3项目说明

本项目采用Gradle构建，目前使用了SSM、Shiro、Ehcache、Spring JdbcTemplate、Druid、Mysql。权限信息以及用户信息用SSM做，Ehcache作为Shiro的缓存，认证信息，和授权信息将会缓存。OAuth2的表操作用JdbcTemplate实现的。Shiro的资源url和角色权限目前在配置文件中写死了，后面参考下博客在解决。MongoDB也可以考虑作为Mysql的替代，对于博客，论坛之类的可以用它来存储。

本项目采用了Java Config和XML混合配置方法，主要是对Shiro的Java Config不会，毕竟资料太少，有空可以去研究研究。。
