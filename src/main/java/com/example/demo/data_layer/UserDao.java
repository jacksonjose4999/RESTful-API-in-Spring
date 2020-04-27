package com.example.demo.data_layer;

import com.example.demo.document.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserDao implements Dao<User>{
    public static final int USER_CREATED = 1;
    public static final int USER_UPDATED = 0;
    public static final int USER_NOT_FOUND = 0;
    public static final int USER_DELETED = 1;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> get(int id) {
        return userRepository.findById(id);
    }

    @Override
    public Collection<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public int save(User user) {
        userRepository.save(user);
        return 1;
    }

    @Override
    public int update(User newUser) {
        AtomicInteger flag = new AtomicInteger();
        userRepository.findById(newUser.getId())
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setAddress(newUser.getAddress());
                    user.setAge(newUser.getAge());
                    flag.set(0);
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    flag.set(1);
                    return userRepository.save(newUser);
                });
        return flag.intValue();
    }

    @Override
    public int delete(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Page<User> getPage(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
