package com.example.demo.controller;

import com.example.demo.domain.Client;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/")
public class UserManagmentController {

    private final ClientService service;

    @Autowired
    public UserManagmentController(ClientService service) {
        this.service = service;
    }


    @Secured("ROLE_USER")
    @GetMapping("/users")
    public List<Client> getUsers() {
        return service.getClients();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping(
            value = "/user",
            produces = "application/json",
            method = RequestMethod.POST)
    public void addUser(@RequestBody Client user) {
        service.addUser(user);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
