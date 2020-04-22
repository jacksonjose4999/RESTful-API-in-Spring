package com.example.demo.repository;

import com.example.demo.document.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer>, UserRepositoryCustom{
    User findByNameAndAddress(String name, String address);
}

interface UserRepositoryCustom {
    public List<User> findUsersByAgeAndName(int age, String name);
}

class UserRepositoryImpl implements UserRepositoryCustom {
    private final MongoOperations operations;

    @Autowired
    public UserRepositoryImpl(MongoOperations operations) {
        this.operations = operations;
    }


    @Override
    public List<User> findUsersByAgeAndName(int age, String name) {
        Query searchQuery = new Query();
        searchQuery.addCriteria(Criteria.where("age").is(age));
        searchQuery.addCriteria(Criteria.where("name").is(name));
        List<User> users = operations.find(searchQuery, User.class);
        return users;
    }
}