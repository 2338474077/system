package com.bivin.service.impl;
import com.bivin.mapper.BrandMapper;
import com.bivin.pojo.Brand;
import com.bivin.pojo.PageBean;
import com.bivin.service.BrandService;
import com.bivin.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;


public class BrandServiceImpl implements BrandService {
    //1、创建对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 1、查询所有信息
     *
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        // 2. 获取Session
        SqlSession sqlSession = factory.openSession();
        // 3. 映射出BrandMapper接口
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用BrandMapper接口中的查询所有的方法
        List<Brand> brands = brandMapper.selectAll();

        // 释放资源
        sqlSession.close();
        return brands;
    }


    /**
     * 2、添加学生学籍信息
     *
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        // 2. 获取Session
        SqlSession sqlSession = factory.openSession();
        // 3. 映射出BrandMapper接口
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用BrandMapper接口中的查询所有的方法
        brandMapper.add(brand);

        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();

    }

    @Override
    public void update(Brand brand) {
        // 2. 获取Session
        SqlSession sqlSession = factory.openSession();
        // 3. 映射出BrandMapper接口
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用BrandMapper接口中的查询所有的方法
        brandMapper.update(brand);

        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();

    }



    /**
     *  通过id删除
     */
    public void delete(int ids){

        // 2. 获取Session
        SqlSession sqlSession = factory.openSession();
        // 3. 映射出BrandMapper接口
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用BrandMapper接口中的批量删除的方法
        brandMapper.delete(ids);

        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();
    }


    /**
     * 3、批量删除
     */
    @Override
    public void deleteByIds(int[] ids) {

        // 2. 获取Session
        SqlSession sqlSession = factory.openSession();
        // 3. 映射出BrandMapper接口
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        // 4. 调用BrandMapper接口中的批量删除的方法
        brandMapper.deleteByIds(ids);

        // 5. 提交事务
        sqlSession.commit();
        // 6. 释放资源
        sqlSession.close();

    }




    /**
     * 4、查询当前页数据功能 & 查询数据库学生学籍信息的总个数
     *
     *
     *
     *  @param begin ： 当前页数
     *  @param size  ： 每一页的展示数据个数
     */
    @Override
    public PageBean<Brand> selectByPage(int begin,int size) {
        // 4.2. 获取Session
        SqlSession sqlSession = factory.openSession();
        // 4.3. 映射出BrandMapper接口
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        /**
         * 在这里进行一下SQL语句分页查询的计算
         *    如： select * from emp limit 0,8;    # 计算公式：起始索引(页数)从0开始   起始索引 =(当前查询的页数-1)*每页展示数据数
         *    我们拿到的客户端请求的页数是第begin页，那么我们的SQL查询起始索引就是（begin-1）* size
         */

        // 4.4、计算起始索引
        int begin1 =(begin-1)*size;

        // 4.5、计算每页展示的数据个数
        int size1 =size;

        /**
         *  4.6、
         */
        List<Brand> brands =brandMapper.selectByPage(begin1,size1);

        /**
         * 4.7、
         */
        int totalCount =brandMapper.selectTotalCount();

        /**
         *  4.8、
         *      把 6、7 查询出来的数据封装到PageBean对象的属性当中 返回给前端
         */
        PageBean<Brand> pageBean =new PageBean<>();
        // 把数据封装到PageBean对象属性当中
        pageBean.setRows(brands);
        pageBean.setTotalCount(totalCount);

        /**
         * 4.9、把pageBean对象返回给前端（属性中已经封装好查询出来的数据咯）
         */
        sqlSession.close();
        return pageBean;

    }

    /**
     *   5、   分页模糊条件查询功能
     * @param begin ：当前页数
     * @param size ： 每页中展示的数据数
     * @param brand ： 模糊条件查询的数据
     * @return
     */

    @Override
    public PageBean<Brand> selectByPageAndCondition(int begin, int size, Brand brand) {
        // 5.2. 获取Session
        SqlSession sqlSession = factory.openSession();
        // 5.3. 映射出BrandMapper接口
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);


        /**
         * 5.4
         *
         * // !!!! 1、这里要进行一下判断 不判断的话 如果有为null的 那下面模糊处理 就成了 %null%  就会使SQL语句错误了
         * // !!!! 2、还要进行分页查询的计算
         */
        // 处理模糊表达式
        String brandName =brand.getBrandName();
        if (brandName!=null && brandName.length() >0){   // 这里要进行一下判断 不判断的话 如果有为null的 那下面模糊处理 就成了 %null%  就会使SQL语句错误了

            brand.setBrandName("%" +brandName+ "%");  // 再把客户端请求的数据做一下模糊表达式处理（如："%华为%")），然后再封装到属性当中 传给代理接口进行SQL语句查询
            System.out.println(brand.getBrandName());
        }

        String companyName =brand.getCompanyName();
        if (companyName!=null && companyName.length() >0){   // 不为null 说明前端请求的有值
            brand.setCompanyName("%" +companyName+ "%");  // 再把客户端请求的数据做一下模糊表达式处理（如："%华为%")），然后再封装到属性当中 传给代理接口进行SQL语句查询
        }

        // 分页计算
        int begin1 =(begin-1)*size;
        int size1 =size;


        List<Brand> brands =brandMapper.selectByPageAndCondition(begin1,size1,brand); // 这里再传的对象属性就是进行过模糊处理的属性数据

        /**
         *  5.5、 调用BrandMapper接口中的查询 "模糊查询出来的总商品数量" 功能方法 （ 把模糊查询查询出来的总商品数量查询出来，等会响应给前端 ）
         */
        int counts =brandMapper.selectTotalCountByCondition(brand);

        /**
         *  5.6、 把 5.4、5.5 查询出来的结果 封装到PageBean对象属性当中，传递响应给前端
         */

        PageBean<Brand> pageBean =new PageBean<>();
        // 封装数据到属性当中
        pageBean.setTotalCount(counts);
        pageBean.setRows(brands);


        //  释放资源，返回PageBean对象
        sqlSession.close();
        return pageBean;

    }
}

