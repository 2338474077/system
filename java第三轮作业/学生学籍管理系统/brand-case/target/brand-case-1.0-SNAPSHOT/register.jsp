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
                这个url需要注意：如果tomcat服务器的url地址是http://localhost:8080/brand_case_war/格式
                那么这个地方就用/brand_case_war

                这个地方改好之后，注册完成后就会成功跳转到登录页面上
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

    /* 给看不清？添加单击事件 也就是说每点击一次看不清？ 就会重新跳转到/brand-demo/checkCodeServlet
    *   记得加时间戳  因为存在缓存情况
    * */

    document.getElementById("changeImg").onclick = function () {
        document.getElementById("checkCodeImg").src = "/brand-case/checkCodeServlet?"+new Date().getMilliseconds();
    }

</script>
</body>
</html>