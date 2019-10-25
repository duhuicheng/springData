package com.bjsxt.jpa.dao;


import com.bjsxt.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 参数一：需要映射的实体类
 * 参数二：对应主键的类型
 */
public interface UserRepository extends JpaRepository<User, Integer> {
}
