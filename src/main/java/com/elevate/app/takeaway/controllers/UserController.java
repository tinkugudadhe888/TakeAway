package com.elevate.app.takeaway.controllers;

import com.elevate.app.takeaway.model.CreadModel;
import com.elevate.app.takeaway.model.ResponseModel;
import com.elevate.app.takeaway.model.UserModel;
import com.elevate.app.takeaway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseModel createUser(@Valid @RequestBody UserModel userModel) {
        ResponseModel model = new ResponseModel();
        model.id = userService.createUser(userModel);
        model.message = "User Created";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

//    @PostMapping("/login/name,pass")
//    public ResponseModel login(@Valid @RequestParam ("Name:")String name ,@RequestParam("Pass") String pass) {
//        ResponseModel model = new ResponseModel();
////      model.data = userService.getAllUsers().stream().map(n->name).collect(Collectors.toList()).stream().map(p->pass).collect(Collectors.toList());
//        model.data = Collections.singletonList(userService.getAllUsers().stream().allMatch(p -> Boolean.parseBoolean(pass)));
//        model.message = "User Found";
//        model.responseCode = HttpStatus.OK.value();
//        return model;
//    }
    @GetMapping("/login")
    public ResponseModel signIn(@Valid @RequestParam("username") String username,
                                @Valid @RequestParam("password") String password) {
        ResponseModel model = new ResponseModel();
        model.data= Collections.singletonList(userService.SignIn(username, password));
        model.message = "User Found";
        model.responseCode = HttpStatus.FOUND.value();
        return model;
    }
    @PostMapping("/signin")
    public ResponseModel signInPsot(@Valid @RequestBody CreadModel creadModel) {
        ResponseModel model = new ResponseModel();
        model.data= Collections.singletonList(userService.SignIn(creadModel.getName(), creadModel.getPassword()));
        model.message = "User Found";
        model.responseCode = HttpStatus.OK.value();
        return model;
    }

    @PutMapping("/update/{userId}")
    public ResponseModel updateUser(@Valid @RequestBody UserModel userModel, @PathVariable long userId) {
        ResponseModel model = new ResponseModel();
        model.id = userService.updateUser(userModel, userId);
        model.message = "User Created";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @GetMapping("/users")
    public ResponseModel getAllUsers() {
        ResponseModel model = new ResponseModel();
        model.data = Collections.singletonList(userService.getAllUsers());
        model.message = "Users Found";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @GetMapping("/city")
    public ResponseModel getUsersByCity(@RequestParam String city) {
        ResponseModel model = new ResponseModel();
        model.data = Collections.singletonList(userService.getUsersByCity(city));
        model.message = "Users Found";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }
}
