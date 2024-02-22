package com.nhnacademy.doorayProject.repository;

import com.nhnacademy.doorayProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
