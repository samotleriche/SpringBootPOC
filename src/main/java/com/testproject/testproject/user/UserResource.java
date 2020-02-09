package com.testproject.testproject.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    public UserResource(UserDaoService service) {
        this.service = service;
    }

    private UserDaoService service;

    // GET ALL USERS
    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return service.findAll();
    }

    // GET A USER
    @GetMapping(path = "/users/{id}")
    public User getUser(@PathVariable int id) {
        User user = service.findOne(id);
        if(user == null)
            throw new UserNotFoundException("id-"+ id);

        return user;
    }

    // CREATE NEW USER
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody User user) {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    // DELETE A USER
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteUser(id);
        if(user == null){
            throw new UserNotFoundException("id-" + id);
        }
    }

}
