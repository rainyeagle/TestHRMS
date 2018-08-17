<%--
  Created by IntelliJ IDEA.
  User: RE
  Date: 2018/8/8
  Time: 13:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>注册</title>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
</head>
<body>
<form>
    <label for="username">用户名</label><input type="text" name="username" id="username"/>
    <br>
    <label for="password">密码</label><input type="password" name="password" id="password"/>
    <br>
    <label for="confirmPwd">确认密码</label><input type="password" name="confirmPwd" id="confirmPwd"/>
    <br>
    <button type="button" onclick="register();">注册</button>
</form>
</body>
<script>
    var username = document.getElementById("username");
    var password = document.getElementById("password");
    var confirmPwd = document.getElementById("confirmPwd");

    /**
     * 页面校验
     */
    function validate() {
        var result = '';
        if (username.value !== null && username.value !== "") {
            $.ajax({
                url: "/user?action=registerCheck",
                dataType: "json",
                data: {
                    "username": username.value
                },
                type: "post",
                success: function (data) {
                    result = data.result;
                }
            });
        }else{

        }
        return result;
    }

    function register() {
        alert(1);
        validate();
    }
</script>
</html>
