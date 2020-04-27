package com.example.demo.services;

import com.example.demo.data_layer.Dao;
import com.example.demo.document.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private Dao<User> userDao;

    public Collection<User> getAllUsers() {
        return userDao.getAll();
    }

    public Optional<User> getUser(int id) {
        return userDao.get(id);
    }

    public int saveUser(User user) {
        return userDao.save(user);
    }

    public int updateUser(User user) {
        return userDao.update(user);
    }

    public int deleteUser(int id) {
        return userDao.delete(id);
    }

    @Transactional(readOnly = true)
    public Page<User> findByPage(int page, int size) {
        Pageable pageable ;

//        if (sort == null) {
//            pageable = PageRequest.of(page, size);
//        }
            pageable = PageRequest.of(page, size);

        return userDao.getPage(pageable);
    }
}


