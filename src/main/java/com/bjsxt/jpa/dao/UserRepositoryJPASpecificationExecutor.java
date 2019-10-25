package com.bjsxt.jpa.dao;

import com.bjsxt.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepositoryJPASpecificationExecutor extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
}
