package com.kou.test;

import com.kou.dao.IUserDao;
import com.kou.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.apache.ibatis.io.Resources;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


/**
 * @author JIAJUN KOU
 * 测试类
 */

public class MybatisCRODTest {

    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    //执行初始化的操作
    @Before
    public void init() throws Exception{
        //1.读取配置文件，是为了加载信息。
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(in);
        //SqlSessionFactory factory=builder.build(in);
        //3.使用工厂生产SqlSession对象
        sqlSession=factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);

    }

    @After
    public void destroy() throws Exception{
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() throws Exception{

        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }



    }
    @Test
    public void testSaveUser() throws Exception{
        User user=new User();
        user.setUsername("mybatis Username");
        user.setAddress("扬州");
        user.setSex("男");
        user.setBirthday(new Date());

        //5.使用代理对象执行方法
        userDao.saveUser(user);


    }
    @Test
    public void testUpdateUser() throws Exception{
        User user=new User();
        user.setId(52);
        user.setUsername("mybatis UpdateUser");
        user.setAddress("北京");
        user.setSex("女");
        user.setBirthday(new Date());

        userDao.updateUser(user);


    }
    @Test
    public void testDeleteUser() throws Exception{

        userDao.deleteUser(53);

    }
    @Test
    public void testFindOne() throws Exception{

        User user = userDao.findById(41);
        System.out.println(user);

    }
    @Test
    public void testFindByUsername() {

        List<User> users = userDao.findByUsername("%王%");
        for (User user : users) {
            System.out.println(user);
        }

    }
    @Test
    public void testFindTotal() {

        int total = userDao.findTotal();
        System.out.println(total);

    }
}
