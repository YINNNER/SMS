<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
    <link href="css/login.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="two">
    <div class="container">
        <div class="web_qr_login" id="web_qr_login" style="display: block; height: 235px;">

            <!--登录-->
            <div class="web_login" id="web_login">
                <div class="login-box">
                    <div class="login_form">
                        <form action="servlet/login" id="login_form" class="loginForm">
                            <div class="uinArea" id="uinArea">
                                <label class="input-tips" for="u">账号：</label>
                                <div class="inputOuter" id="uArea">
                                    <input type="text" name="AdminName" id="u" class="inputstyle"/>
                                </div>
                            </div>
                            <div class="pwdArea" id="pwdArea">
                                <label class="input-tips" for="p">密码：</label>
                                <div class="inputOuter" id="pArea">
                                    <input type="password" name="AdminPwd" id="p" class="inputstyle"/>
                                </div>
                            </div>

                            <div>
                                <input type="submit" value="登录" class="button_blue"/>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
