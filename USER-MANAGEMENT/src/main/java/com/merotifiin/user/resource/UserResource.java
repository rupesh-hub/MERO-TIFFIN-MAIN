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

    /**
     * create new user
     *
     * @param userRequestPojo
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(final @RequestBody UserRequestPojo userRequestPojo) {
        return new ResponseEntity(userService.createUser(userRequestPojo), HttpStatus.CREATED);
    }

    /**
     * get all user with pagination
     *
     * @param limit
     * @param page
     * @return
     */
    @GetMapping("/all")
    public ResponseEntity<?> allUser(@RequestParam(name = "limit", defaultValue = "10") final int limit,
                                     @RequestParam(name = "page", defaultValue = "1") final int page) {
        return new ResponseEntity(userService.allUser(page, limit), HttpStatus.OK);
    }

    /**
     * find user by email
     *
     * @param email
     * @return
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> userByEmail(@PathVariable final String email) {
        return new ResponseEntity(userService.getByEmail(email), HttpStatus.OK);
    }

    /**
     * find user by user id
     *
     * @param userId
     * @return
     */
    @GetMapping("/userId/{userId}")
    public ResponseEntity<?> userByUserId(@PathVariable final String userId) {
        return new ResponseEntity(userService.getByUserId(userId), HttpStatus.OK);
    }

    /**
     * find user by phone
     *
     * @param phone
     * @return
     */
    @GetMapping("/phone/{phone}")
    public ResponseEntity<?> userWithPhone(@PathVariable final String phone) {
        return new ResponseEntity(userService.getByPhone(phone), HttpStatus.OK);
    }

    /**
     * assign role to existing user
     *
     * @param role
     * @param email
     * @return
     */
    @PutMapping("/assign-role/role/{role}/email/{email}")
    public ResponseEntity<?> assignRole(@PathVariable final String role,
                                        @PathVariable final String email) {
        return new ResponseEntity(userService.assignRole(role, email), HttpStatus.OK);
    }

    /**
     * remove role of existing user
     *
     * @param role
     * @param email
     * @return
     */
    @PutMapping("/remove-role/role/{role}/email/{email}")
    public ResponseEntity<?> removeRole(@PathVariable final String role,
                                        @PathVariable final String email) {
        return new ResponseEntity(userService.removeRole(role, email), HttpStatus.OK);
    }

}
