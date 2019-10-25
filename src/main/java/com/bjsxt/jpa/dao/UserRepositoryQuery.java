package com.bjsxt.jpa.dao;

import com.bjsxt.jpa.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepositoryQuery extends Repository<User, Integer> {

    /**
     * 使用sql来查询
     * @param address
     * @return
     */
    @Query(value = "select * from user where address = ?", nativeQuery = true)
    List<User> queryByAddressSQL(String address);

    /**
     * 执行更新操作，必须配合事务执行否则会回滚
     * @param age
     * @param address
     */
    @Query(value = "update user set age = ? where address = ?", nativeQuery = true)
    @Modifying
    void UpdateByAddressSQL(int age, String address);
}
