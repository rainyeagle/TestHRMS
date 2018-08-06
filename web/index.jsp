<%--
  Created by IntelliJ IDEA.
  User: RE
  Date: 2018/8/3
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>欢迎</title>
</head>
<body>
<form id="loginForm" method="post">
    <label for="username">用户名</label><input type="text" name="username" id="username"/>
    <label for="password">密码</label><input type="password" name="password" id="password"/>
    <button type="button" onclick="loginSubmit();">提交</button>
</form>
</body>
<script>
    /**script
     * 提交表单
     */
    function loginSubmit() {
        var loginForm = document.getElementById("loginForm");
        loginForm.action = "/login";
        loginForm.submit();
    }
</script>
</html>
