package com.bivin.web;
import com.bivin.pojo.User;
import com.bivin.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.获取用户注册的用户名和密码
        String username =request.getParameter("username");
        String password =request.getParameter("password");

        // 获取用户输入的验证码
        String checkCode =request.getParameter("checkCode");

        // 获取程序随机生成的验证码    (根据key获取值)
        HttpSession session =request.getSession();
        // Object类型转换成String类型 （用户输入的为String类型 转成String做对比）
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        // 注册之前作对比 如果验证码都不正确就没必要再判断往下走代码 判断查询的user对象是否为null了
        // 程序生成的验证码肯定不会为null 所以比较的时候放在最前面
        // equalsIgnoreCase ： 是不区分大小写比较的方法 （验证码一般不需要比较大小写）
        if (!checkCodeGen.equalsIgnoreCase(checkCode)){
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return; // 直接结束代码即可
        }



        // 2. 传递用户名
        UserService userService =new UserService();
        User user =userService.register_u(username);


        // 3. 判断查询的user对象是否为null  不为null说明查询到该用户了 说明已经注册过了
        if (user !=null){
            // 用户已经存在了，说明注册失败了
            // 注册失败后，还跳转到注册的页面
            request.setAttribute("register_msg","用户名已存在，注册失败");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }else {
            // 用户不存在,把封装到User对象中的数据insert注册到数据库中
            User user1 =new User();
            user1.setUsername(username);
            user1.setPassword(password);
            userService.register_i(user1);

            // 注册成功后，跳转到登录页面
            request.setAttribute("register_msg","注册成功，请登录");//数据储存到request域中
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}