package com.merotifiin.role.resource;

import com.merotifiin.role.entity.Role;
import com.merotifiin.role.service.IRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleResource {

    private final IRoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<?> createRoles(@RequestBody final List<Role> roles) {
        return new ResponseEntity<>(roleService.createRoles(roles), HttpStatus.CREATED);
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<?> getByName(@PathVariable final String roleName) {
        return new ResponseEntity<>(roleService.getByName(roleName), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> allRole() {
        return new ResponseEntity<>(roleService.allRole(), HttpStatus.OK);
    }
}
