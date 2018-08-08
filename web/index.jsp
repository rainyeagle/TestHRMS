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
    <script type="application/javascript" src="js/cookieUtils.js"></script>
    <script type="application/javascript" src="js/jquery-1.9.1.min.js"></script>
</head>
<body>
<form id="loginForm" method="post">
    <label for="username">用户名</label><input type="text" name="username" id="username"/>
    <br/>
    <label for="password">密码</label><input type="password" name="password" id="password"/>
    <br/>
    <input type="checkbox" name="savePassword" id="savePassword"/><label for="savePassword">保存密码</label>
    <input type="checkbox" name="autoLogin" id="autoLogin"/><label for="autoLogin">自动登录</label>
    <br/>
    <button type="button" onclick="login();">提交</button>
</form>
</body>
<script>

    window.onload = function () {
        //判断上次登录是否为自动登录
        var isAutoLogin = getCookie("autoLogin");
        if (isAutoLogin !== "") {
            //赋值
            var username = getCookie("username");
            var password = getCookie("password");
            document.getElementById("username").value = username;
            document.getElementById("password").value = password;
        }
    };

    function login() {
        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;
        checkUsernameAndPassword(username,password);
        // var autoLogin = document.getElementById("autoLogin").checked;
        // if (autoLogin) {
        //     //如果为
        // }
        //loginSubmit();
    }

    function checkUsernameAndPassword(username, password) {
        $.ajax({
            url: "/loginCheck",
            dataType: "json",
            data: {
                "username": username,
                "password": password
            },
            type:"post",
            success:function(){
                alert("123");
            }
        })
    }

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
