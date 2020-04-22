package com.example.demo.resource_controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.demo.document.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

    @Override
    public EntityModel<User> toModel(User user) {

        return new EntityModel<>(user,
                linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAll()).withRel("all"));
    }
}

