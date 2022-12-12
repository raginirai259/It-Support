package com.support.it.repository;


import com.support.it.entity.User;
import com.support.it.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository()
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
