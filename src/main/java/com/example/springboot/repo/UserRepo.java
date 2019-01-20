package com.example.springboot.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.UserModel;

public interface UserRepo extends JpaRepository<UserModel, String>{

}
