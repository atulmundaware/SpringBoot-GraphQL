package com.example.springboot.service.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springboot.model.UserModel;
import com.example.springboot.repo.UserRepo;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllUserDataFetcher implements DataFetcher<List<UserModel>>{

	@Autowired
	UserRepo bookRepo;
	
	
	@Override
	public List<UserModel> get(DataFetchingEnvironment environment) {
		// TODO Auto-generated method stub
		return bookRepo.findAll();
	}

}
