package com.winter.security.repository;

import com.winter.security.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @ClassName : UserRepository
 * @Description :
 * @Author : Winter
 * @Date: 2020-09-21 11:07
 */
public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {
    User findByUsername(String username);
}
