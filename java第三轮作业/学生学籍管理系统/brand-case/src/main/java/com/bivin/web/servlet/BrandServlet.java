package com.bivin.web.servlet;
import com.alibaba.fastjson.JSON;
import com.bivin.pojo.Brand;
import com.bivin.pojo.PageBean;
import com.bivin.service.BrandService;
import com.bivin.service.impl.BrandServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;


@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{


    private BrandService brandService =new BrandServiceImpl();

    /**
     *  1、
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // 1. 调用BrandServiceImpl完成查询所有功能
        List<Brand> brands =brandService.selectAll();

        // 2. 将Java对象数据转换成JSON格式数据，响应给前端
        response.setContentType("text/json;charset=utf-8"); // 中文乱码问题
        String toString = JSON.toJSONString(brands);

        // 3. 响应给前端数据
        response.getWriter().write(toString);
    }

    /**
     *  2、
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


        BufferedReader bufferedReader =request.getReader();
        String rl =bufferedReader.readLine();   // 拿到前端请求的JSON数据

        // 1. 将JSON数据转换成Java数据
        Brand brand =JSON.parseObject(rl,Brand.class);

        // 2. 调用BrandService接口中的添加商品方法
        brandService.add(brand);

        // 3. 响应给前端
        response.getWriter().write("success");
    }

    /**
     *
     *  修改功能
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        // String id = request.getParameter("id");
        BufferedReader bufferedReader =request.getReader();
        String rl =bufferedReader.readLine();   // 拿到前端请求的JSON数据

        // 2. 将JSON数据转换成Java数据
        Brand brand =JSON.parseObject(rl,Brand.class);


        // 3. 调用BrandService接口中的添加商品方法
        brandService.update(brand);

        // 4. 响应给前端
        response.getWriter().write("success");
    }

    /**
     * 3、
     *      思路：把客户端请求的删除id 封装到int数组里面 然后传递给代理接口 供SQL语句使用 （SQL语句要用增强for 遍历该数组删除数据即可）
     */

    public void deleteByIds(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{


        BufferedReader bufferedReader =request.getReader();
        String params =bufferedReader.readLine();
        // System.out.println(params);   // 拿到前端请求的JSON数据格式： [1,2,3,4,5]

        // 1、把JSON数据转换成Java的数组格式
        int[] ids =JSON.parseObject(params,int[].class);

        // 2、调用业务逻辑层的批量删除方法
        brandService.deleteByIds(ids);

        // 3、删除完成后，响应给前端
        response.getWriter().write("success");


    }

    /**
     *  4、查询当前页码数据功能 &
     */
    public void selectByPage(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{


        // 1、接收前端请求过来的 ”当前页码数“ 和 “每页展示的数据个数”


        String _currentPage =request.getParameter("currentPage");
        String _pageSize =request.getParameter("pageSize");

        // 2、把String字符串类型转换成int类型
        int currentPage =Integer.parseInt(_currentPage);
        int pageSize =Integer.parseInt(_pageSize);

        // 3、调用查询方法 （把 ”当前页码“ 和 “每页展示的数据个数” 传递过去）
        PageBean<Brand> o =brandService.selectByPage(currentPage,pageSize);

        // 4. 将Java对象数据转换成JSON格式数据，响应给前端
        response.setContentType("text/json;charset=utf-8"); // 中文乱码问题
        String toString = JSON.toJSONString(o);

        // 5. 响应给前端数据
        response.getWriter().write(toString);
    }


    /**
     *  4、
     */
    public void selectByPageAndCondition(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        // 解决POST乱码问题
        request.setCharacterEncoding("UTF-8");

        // 1、接收前端请求过来的 ”当前页码数“ 和 “每页展示的数据个数” 和”模糊条件查询请求的数据参数“  （模糊条件查询请求的数据参数：前端是以JSON数据请求过来的）

        String _currentPage =request.getParameter("currentPage");
        String _pageSize =request.getParameter("pageSize");

        // 2、把String字符串类型转换成int类型
        int currentPage =Integer.parseInt(_currentPage);
        int pageSize =Integer.parseInt(_pageSize);

        // 3、获取前端模糊条件查询的请求参数
        // 3.1、先抓包
        BufferedReader bufferedReader =request.getReader();
        // 3.2、读一行
        String params =bufferedReader.readLine(); // 拿到前端模糊查询请求参数的JSON数据格式

        // 3.3、将JSON数据 转换成 Java对象形式
        Brand brand =JSON.parseObject(params,Brand.class);  // brand对象属性中封装好了前端模糊请求的数据

        // 4、 调用 分页模糊条件查询功能
        PageBean<Brand> o =brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        String jsonString = JSON.toJSONString(o);
        // 5、 将查询出来的Java对象数据转换成JSON格式数据，响应给前端
        response.setContentType("text/json;charset=utf-8"); // 中文乱码问题
        // 6、 响应给前端数据
        response.getWriter().write(jsonString);

    }


    public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{

        // 1、抓包 （ 前端是以JSON数据格式请求数据的，所以需要先抓包 ）
        BufferedReader bufferedReader =request.getReader();
        String params =bufferedReader.readLine();
        // System.out.println(params);   // 拿到前端请求的JSON数据格式： [1,2,3,4,5]

        // 2、把JSON数据转换成Java的数组格式
        int[] ids =JSON.parseObject(params,int[].class);

        // 3、调用业务逻辑层的批量删除方法 （将数组传到sql语句当中去）
        brandService.deleteByIds(ids);

        // 4、删除完成后，响应给前端成功的标识
        response.getWriter().write("success");

    }
}