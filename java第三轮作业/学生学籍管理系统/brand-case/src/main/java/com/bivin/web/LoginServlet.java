package com.bivin.web;
import com.bivin.pojo.User;
import com.bivin.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1、接收客户端用户名和密码
        String username =request.getParameter("username");
        String password =request.getParameter("password");

        // 获取复选框数据
        String remember =request.getParameter("remember");

        // 2、调用service层进行查询
        UserService userService =new UserService();
        User user =userService.login(username,password);

        // 3、判断查询是否有结果
        if (user != null){
            // 判断user不为null说明登录成功了

            // 判断用户是否勾选了记住我 remember
            // 这里用："1".equals(remember) 而不用remember.equals("1")

            if ("1".equals(remember)){
                // 勾选了,发送Cookie

                // 1 创建Cookie对象
                Cookie c_username =new Cookie("username",username);
                Cookie c_password =new Cookie("password",password);
                // 设置Cookie数据在客户端存活的时间
                c_username.setMaxAge(60*60*24*60);
                c_password.setMaxAge(60*60*24*60);
                // 2 发送Cookie
                response.addCookie(c_username);
                response.addCookie(c_password);

            }

            // 2. 把user查询出来的数据先封装到Session域当中
            HttpSession httpSession =request.getSession();
            // 存储到Session域中
            httpSession.setAttribute("user",user);

            // 1.登录成功
            String path =request.getContextPath();
            response.sendRedirect(path+"/selectAllServlet");

        } else {
            // 登录失败
            // 储存错误提示信息到request域当中 转发给login.jsp
            request.setAttribute("login_msg","用户名或密码错误");
            // 跳转到登录的login.jsp页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
