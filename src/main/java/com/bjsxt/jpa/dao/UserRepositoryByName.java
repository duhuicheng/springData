package com.bjsxt.jpa.dao;

import com.bjsxt.jpa.entity.User;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepositoryByName extends Repository<User, Integer> {

    /**
     * 根据地址查询
     * @param address
     * @return
     */
    List<User> findByAddress(String address);

    /**
     * 根据地址和年龄查询
     * @param address
     * @param age
     * @return
     */
    List<User> findByAddressAndAge(String address, int age);

    /**
     * 根据通配符查询
     * @param address
     * @return
     */
    List<User> findByAddressLike(String address);
}
