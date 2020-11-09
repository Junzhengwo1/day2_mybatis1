package com.kou.dao;

import com.kou.domain.QueryVo;
import com.kou.domain.User;
import org.apache.ibatis.annotations.Select;

import javax.management.Query;
import java.util.List;

/**
 * @author dell
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    //@Select("select * from user")
    List<User> findAll();

    /**
     * 保存User
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id删除用户
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 根据Id查询用户
     * @param userId
     * @return
     */
    User findById(Integer userId);

    /**
     * 根据姓名模糊查询用户
     * @param name
     * @return
     */
    List<User> findByUsername(String name);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();

    /**
     * 根据查询条件查询用户， 综合条件查询
     * @param vo
     * @return
     */
    List<User> findUserByVo(QueryVo vo);


}
