package com.example.springboot.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.example.springboot.service.datafetcher.AllUserDataFetcher;
import com.example.springboot.service.datafetcher.UserFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLService {

	private GraphQL graphQL;
	
	@Value("classpath:users.graphql")
	Resource resource;
	@Autowired
	private AllUserDataFetcher allUsersDataFetcher;
	@Autowired
	private UserFetcher userDataFetcher;
	
	//This will load the schema at application startup process
	@PostConstruct
	private void loadSchema() throws IOException {
		
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", typeWiring -> typeWiring
							.dataFetcher("allUsers", allUsersDataFetcher)
							.dataFetcher("user", userDataFetcher))
				.build();
	}

	public GraphQL getGraphQL() {
		return graphQL;
	}

	
}
