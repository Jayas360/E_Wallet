package com.jbdl.user_service.repository;

import com.jbdl.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
