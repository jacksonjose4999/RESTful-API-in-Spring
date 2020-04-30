package com.example.demo.config;

import com.example.demo.repository.LoginRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = LoginRepository.class)
public class MongoDBConfigLoginRepository {

}
