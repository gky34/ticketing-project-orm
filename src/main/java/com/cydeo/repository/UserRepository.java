package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);

    List<User> findAllByRoleDescriptionIgnoreCase(String description);
}

/**
 *  @Transactional
 *  Driver Query we need to use this annotation

    @Modifying
    For Native Query or JOQuery
 */