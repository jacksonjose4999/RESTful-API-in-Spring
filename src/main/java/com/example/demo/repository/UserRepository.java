package com.example.demo.repository;

import com.example.demo.document.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer>, UserRepositoryCustom, PagingAndSortingRepository<User, Integer> {
    User findByNameAndAddress(String name, String address);
    Page<User> findAll(Pageable pageRequest);
}

interface UserRepositoryCustom {
    List<User> findUsersByAgeAndName(int age, String name);
}

class UserRepositoryImpl implements UserRepositoryCustom {
    private final MongoOperations operations;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoOperations operations, MongoTemplate mongoTemplate) {
        this.operations = operations;
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public List<User> findUsersByAgeAndName(int age, String name) {
        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("age").is(age));
        searchQuery.addCriteria(Criteria.where("name").is(name));
        return operations.find(searchQuery, User.class);
    }
}