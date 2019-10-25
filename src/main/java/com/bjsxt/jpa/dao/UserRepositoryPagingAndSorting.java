package com.bjsxt.jpa.dao;

import com.bjsxt.jpa.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepositoryPagingAndSorting  extends PagingAndSortingRepository<User, Integer> {
}
