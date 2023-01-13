<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.jsp">登录</a>
    </div>
    <form id="reg-form" action="/brand_case_war/registerServlet" method="post">

        <%-- ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！ ！！！！！！！！！！！--%>
        <%-- /brand_case_war/registerServlet

        --%>

        <table>

            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg" >${register_msg}</span>

                    <%--
                    ${register_msg} 通过EL表达式拿到RegisterServlet路径下封装转发过来的
                     数据 (用户名已存在，注册失败)
                     --%>

                </td>

            </tr>

            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span>
                </td>
            </tr>


            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <img id="checkCodeImg" src="/brand_case_war/checkCodeServlet">
                <%-- ！！！！！！！！！！！！！！！！！！！！！！！！！ --%>
                    <%-- <img id="checkCodeImg" src="/brand_case_war/checkCodeServlet">
                        这个地方和上面一样 也要看tomcat服务器的url格式，然后改成一样， 如果
                        不改的话 注册的地方验证码不会出来
                    --%>
                    <a href="#" id="changeImg" >看不清？</a>
                </td>
            </tr>

        </table>

        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>


    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/brand-case/checkCodeServlet?"+new Date().getMilliseconds();
    }

</script>
</body>
</html>