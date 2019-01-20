package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.service.GraphQLService;

import graphql.ExecutionResult;

@RequestMapping("/users")
@RestController
public class UserController {

	@Autowired
	GraphQLService graphqlService;
	
	@PostMapping("/getAllUsers")
	public ResponseEntity<Object> getAllBooks(@RequestBody String query) {
		ExecutionResult executionResult = graphqlService.getGraphQL().execute(query);
		return new ResponseEntity<Object>(executionResult,HttpStatus.OK);
	}
}
