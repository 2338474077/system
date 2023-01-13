package com.bivin.service;
import com.bivin.mapper.UserMapper;
import com.bivin.pojo.User;
import com.bivin.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class UserService {

    SqlSessionFactory Factory =SqlSessionFactoryUtils.getSqlSessionFactory();
    /**
     *  登录方法
     */
    public User login(String username,String password){

        // 1. 获取SqlSession
        SqlSession sqlSession =Factory.openSession();
        UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
        // 2. 调用UserMapper接口的查询用户和密码方法
        User user =userMapper.select(username,password);
        // 3. 释放资源
        sqlSession.close();
        return user;
    }

    /**
     *  注册方法
     *  1 先通过用户传递的用户名查询数据库判断是否有该用户
     */
    // 1. 获取SqlSession
    public User register_u(String username){
        SqlSession sqlSession =Factory.openSession();
        UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
        // 2. 调用UserMapper接口的查询用户方法
        User user =userMapper.selectByUsername(username);
        // 3. 释放资源
        sqlSession.close();
        return user;
    }

    /**
     * 注册方法
     * 2 用户不存在的时候 把用户注册的用户名和密码insert添加到数据库当中
     *  注意：别忘记提交事务
     */
    public void register_i(User user){
        SqlSession sqlSession =Factory.openSession();
        UserMapper userMapper =sqlSession.getMapper(UserMapper.class);
        // 2. 调用UserMapper接口的查询用户方法
        userMapper.add(user);

        sqlSession.commit();    // 提交事务
        // 3. 释放资源
        sqlSession.close();

    }

}