package com.example.usuarioservice.controllers;

import com.example.usuarioservice.exceptions.ResourceNotFoundException;
import com.example.usuarioservice.exceptions.UserDomainException;
import com.example.usuarioservice.models.UserModel;
import com.example.usuarioservice.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/UserModel")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ArrayList<UserModel> getAllUsers() {
        return this.userService.getUsers();
    }
    @PostMapping
    public UserModel createUser(@RequestBody UserModel user) throws UserDomainException {
        return this.userService.save(user);
    }
    @GetMapping(path="/byId/{id}")
    public UserModel getUserById(@PathVariable("id") Long id) throws UserDomainException, ResourceNotFoundException {
        return this.userService.findById(id);
    }
    @GetMapping(path = "/byName/{name}")
    public UserModel getUserByName(@PathVariable("name") String name) {
        return this.userService.findByName(name);
    }
    @PutMapping(path = "/{id}")
    public UserModel updateById(@RequestBody UserModel user, @PathVariable("id") long id)throws UserDomainException,ResourceNotFoundException {
        return this.userService.updateById(user,id);
    }
    @DeleteMapping(path = "{id}")
    public boolean deleteById(@PathVariable("id") long id) {
        boolean ok =this.userService.deleteById(id);
        if(ok){
            System.out.println("User deleted successfully");
            return true;
        }
        System.out.println("User not deleted successfully");
        return false;
    }
    @GetMapping(path="/validate/{username}/{password}")
    public ResponseEntity<Boolean> validarUsuario(@PathVariable("username")String username, @PathVariable("password")String password){
        return this.userService.validateUserCredentials(username,password);
    }

}
