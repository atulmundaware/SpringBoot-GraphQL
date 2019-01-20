package com.example.springboot.service.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.model.UserModel;
import com.example.springboot.repo.UserRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookUserFetcher implements DataFetcher<UserModel>{

	@Autowired
	UserRepo bookRepo;
	
	
	@Override
	public UserModel get(DataFetchingEnvironment environment) {
		return bookRepo.getOne(environment.getArgument("id"));
	}

}
