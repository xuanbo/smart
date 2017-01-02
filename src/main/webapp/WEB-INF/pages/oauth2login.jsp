<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录并授权</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div>你将授权给 ${clientName} </div>
<div class="error">${error}</div>

<form action="" method="post">
    用户名：<input type="text" name="username"><br/>
    密码：<input type="password" name="password"><br/>
    <input type="submit" value="登录并授权">
</form>

</body>
</html>
