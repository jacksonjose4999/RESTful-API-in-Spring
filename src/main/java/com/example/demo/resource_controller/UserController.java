package com.example.demo.resource_controller;

import com.example.demo.document.User;
import com.example.demo.exceptions.CouldNotSaveException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.data_layer.UserDao;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UserController {
    @Autowired
    private UserModelAssembler userModelAssembler;
    @Autowired
    private UserService userService;
    @Autowired
    UserRepository userRepository;
    public UserController() {
    }

    @GetMapping("/users/page")
    public Page<User> findByPages(Pageable pageRequest) {
        return userService.findByPage(pageRequest);
    }

    @GetMapping("/users")
    CollectionModel<EntityModel<User>> getAll() {
        List<EntityModel<User>> users = userService.getAllUsers().stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return new CollectionModel<>(users,
                linkTo(methodOn(UserController.class).getAll()).withSelfRel());
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUser(@PathVariable int id) {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent()) {
            return userModelAssembler.toModel(user.get());
        }
        throw new UserNotFoundException(Integer.toString(id));
    }

    @PostMapping("/users")
    public ResponseEntity<?> postUser(@Valid @RequestBody User newUser) {
        if(userService.saveUser(newUser) == UserDao.USER_CREATED) {
            EntityModel<User> entityModel = userModelAssembler.toModel(newUser);
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }
        throw new CouldNotSaveException(Integer.toString(newUser.getId()));
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User newUser, @PathVariable int id) {

        if (userService.updateUser(newUser)==UserDao.USER_CREATED){
            EntityModel<User> entityModel = userModelAssembler.toModel(newUser);
            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
        }
        else {
            EntityModel<User> entityModel = userModelAssembler.toModel(newUser);
            return ResponseEntity.ok().body(entityModel);
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
        if (userService.deleteUser(id) == UserDao.USER_DELETED) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

