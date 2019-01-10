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
    <label for="username">用户名</label><input type="text" name="username" id="username" onblur="checkUsername();"/>
    <span id="remark"></span>
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
     * 检查用户名
     */
    function checkUsername() {
        var flag = false;
        var remark = document.getElementById("remark");
        var result = usernameValidate();
        if (result === "用户名不能为空") {
            remark.style.color = 'red';
            remark.innerHTML = "用户名不能为空";
            username.value = '';    //清空表单项
        } else if (result.substr(0, 4) === 'E200') {
            remark.style.color = 'red';
            remark.innerHTML = result.substr(4);
            username.value = '';    //清空表单项
        } else if (result.substring(0, 3) === "100") {
            remark.style.color = 'red';
            remark.innerHTML = "用户信息已存在";
        } else if (result.substring(0, 3) === "101") {
            remark.style.color = 'green';
            remark.innerHTML = "";
            flag = true;
        }
        return flag;
    }

    /**
     * 检查密码
     */
    function checkPassword() {

        if (password.value.length === 0 || password.value.trim() === '') {

        }
    }

    /**
     * 后台确认用户名是否可用
     */
    function usernameValidate() {
        var result = '';
        if (username.value !== null && username.value !== "" && username.value.trim() !== "") {
            if (checkLegality(username.value, 1)) {
                result = "E200 用户名'" + username.value + "'包含不合法字符，请确认后重新填写！";
            } else {
                $.ajax({
                        async: false,   //同步，异步会出现无法赋值的情况
                        url: "/user?action=registerCheck",
                        dataType: "json",
                        data: {
                            "username": username.value
                        },
                        type: "post",
                        success: function (data) {
                            result = data.result.toString();
                        }

                    }
                );
            }
        } else {
            result = "用户名不能为空";
        }
        return result;
    }

    /**
     *  检查用户名及密码的合法性
     *  @param str 需要检查的字符串
     *  @param type 1为用户名，2为密码
     */
    function checkLegality(str, type) {
        var illegalCharacters;
        if (type === 1) {
            illegalCharacters = ['&', '\\', '/', '*', '>', '<', '@', '!', ' '];
        } else if (type === 2) {
            illegalCharacters = ['&', '\\', '/', '*', '>', '<', '@', '!', ' '];
        }
        for (var i = 0; i < illegalCharacters.length; i++) {
            for (var j = 0; j < str.length; j++) {
                if (illegalCharacters[i] === str.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }

    function register() {
        //validate();
    }
</script>
</html>
