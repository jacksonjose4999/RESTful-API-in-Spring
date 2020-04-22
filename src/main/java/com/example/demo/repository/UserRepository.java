package com.example.demo.repository;

import com.example.demo.document.User;
import com.jayway.jsonpath.Criteria;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer>{
    User findByNameAndAddress(String name, String address);
}

//interface UserRepositoryCustom {
//
//    int updateAddress(String address, boolean flag);
//
//}
//
//class DomainRepositoryImpl implements UserRepositoryCustom {
//
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    @Override
//    public int updateAddress(String address, boolean flag) {
//
//        Query query = new Query(Criteria.where("address").is(address));
//        Update update = new Update();
//        update.set("display", flag);
//
//        WriteResult result = mongoTemplate.updateFirst(query, update, User.class);
//
//        if(result!=null)
//            return result.getN();
//        else
//            return 0;
//
//    }
//}