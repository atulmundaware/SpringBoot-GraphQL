package com.example.springboot.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.model.UserModel;
import com.example.springboot.repo.UserRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class UserFetcher implements DataFetcher<UserModel>{

	@Autowired
	UserRepo userRepo;
	
	
	@Override
	public UserModel get(DataFetchingEnvironment environment) {
		return userRepo.getOne(environment.getArgument("id"));
	}

}
