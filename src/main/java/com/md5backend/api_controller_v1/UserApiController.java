package com.md5backend.api_controller_v1;

import com.md5backend.baseform.ResponseForm;
import com.md5backend.baseform.UserCreatingForm;
import com.md5backend.model.User;
import com.md5backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users")
    public ResponseEntity<ResponseForm<Iterable<User>>> getUsersApi() {
        ResponseForm<Iterable<User>> responseForm = new ResponseForm<>();
        Iterable<User>               users        = userService.findAllUsers();
        responseForm.setData(users);
        if (!users.iterator().hasNext()) {
            responseForm.setStatus(4);
            responseForm.setMessage("Empty");
        }
        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseForm<User>> getUserByIdApi(@PathVariable("id") Long id) {
        ResponseForm<User> responseForm = new ResponseForm<>();
        Optional<User>           user         = userService.findUserById(id);
        if (!user.isPresent()) {
            responseForm.setStatus(4);
            responseForm.setMessage("Not found");
            responseForm.setData(null);
        }else {
            responseForm.setData(user.get());
        }
        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ResponseEntity<ResponseForm<User>> createUserApi(@RequestBody UserCreatingForm userCreatingForm) {
        ResponseForm<User> responseForm = new ResponseForm<>();
        User               user         = new User();
        user.setName(userCreatingForm.getName());
        user.setAddress(userCreatingForm.getAddress());
        User u = userService.saveUser(user);
        if (u == null) {
            responseForm.setStatus(5);
            responseForm.setMessage("Create user fail.");
        }
        responseForm.setData(u);
        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}/edit", method = RequestMethod.PUT)
    public ResponseEntity<ResponseForm<User>> editUserApi(@PathVariable("id") Long id, @RequestBody UserCreatingForm userCreatingForm) {
        ResponseForm<User> responseForm = new ResponseForm<>();
        User               user         = new User();
        user.setId(id);
        user.setName(userCreatingForm.getName());
        user.setAddress(userCreatingForm.getAddress());
        User u = userService.saveUser(user);
        if (u == null) {
            responseForm.setStatus(5);
            responseForm.setMessage("Save user fail.");
        }
        responseForm.setData(u);
        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{id}/delete", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseForm<User>> deleteUserApi(@PathVariable("id") Long id) {
        ResponseForm<User> responseForm = new ResponseForm<>();
        Optional<User>             user         = userService.findUserById(id);
        userService.deleteUserById(id);
        if (!user.isPresent()) {
            responseForm.setMessage("User not found.");
            responseForm.setStatus(4);
        }else {
            responseForm.setData(user.get());
        }

        return new ResponseEntity<>(responseForm, HttpStatus.OK);
    }

}
