package com.example.demo.resource_controller;

import com.example.demo.document.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.UserRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

    private UserRepository userRepository;
    private UserModelAssembler userModelAssembler;

    public UserController(UserRepository userRepository, UserModelAssembler userModelAssembler) {
        this.userRepository = userRepository;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping("/users")
    CollectionModel<EntityModel<User>> getAll() {
        List<EntityModel<User>> users = userRepository.findAll().stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(users,
                linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Integer.toString(id)));
        return userModelAssembler.toModel(user);
    }

    @PostMapping("/users")
    public ResponseEntity<?> postUser(@Valid @RequestBody User newUser) {
        EntityModel<User> entityModel = userModelAssembler.toModel(userRepository.save(newUser));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> replaceUser(@Valid @RequestBody User newUser, @PathVariable int id) {
        User updatedUser = userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setAddress(newUser.getAddress());
                    user.setAge(newUser.getAge());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });

        EntityModel<User> entityModel = userModelAssembler.toModel(userRepository.save(updatedUser));
        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

