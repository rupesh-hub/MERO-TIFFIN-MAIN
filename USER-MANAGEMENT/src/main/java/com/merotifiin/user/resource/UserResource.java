package com.merotifiin.user.resource;

import com.merotifiin.user.model.UserRequestPojo;
import com.merotifiin.user.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = {"/users"})
@RequiredArgsConstructor
public class UserResource {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(final @RequestBody UserRequestPojo userRequestPojo) {
        return new ResponseEntity(userService.createUser(userRequestPojo), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allUser() {
        return new ResponseEntity(userService.allUser(), HttpStatus.OK);
    }

}
