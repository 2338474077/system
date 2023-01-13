<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>
<body>
<div id="loginDiv" style="height: 350px">
    <form action="/brand_case_war/brand.html" id="form">

        <%--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            这个<form action="/brand_case_war/brand.html" id="form">
            也要看tomcat服务器的url，如果tomcat服务器的url地址是http://localhost:8080/brand_case_war/格式
            这个地方也要换成/brand_case_war 而不是brand-case，要不然输入完用户名和密码登录成功后不会成功跳转到brand.html
            展示数据的页面上
        --%>
        <h1 id="loginMsg">LOGIN IN</h1>
        <div id="errorMsg">${login_msg} ${register_msg}</div>
        <%--
        ${login_msg} 就是我们在LoginServlet资源下登录失败后转发到login页面把
        登录页面展示给用户,并且把转发时储存到request域当中的数据(用户名或密码错误)拿
        到展示在登录页面上  ${login_msg}：EL表达式 拿储存在域中数据的

        ${register_msg} 拿到的是RegisterServlet资源下封装到request域当中的数据通过转发过来
        （注册成功，请登录）展示在登录的页面上
        --%>


        <p>Username:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>

        <p>Password:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>


        <%-- value 的作用就是在复选框中,假设选中了该复选框那么该复选框的值也就是该value的值
                这里remember是复选框 当我们勾选后 该默认值为“1”
        --%>
        <p>Remember:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？</a>
        </div>
    </form>
</div>


</body>

</html>