package org.monitordigital.jtwittery.controller;

import lombok.AllArgsConstructor;
import org.monitordigital.jtwittery.model.user.User;
import org.monitordigital.jtwittery.service.UserService;
import org.monitordigital.jtwittery.service.form.CreateUserForm;
import org.monitordigital.jtwittery.service.form.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserForm createUserForm) {
        User newUser = userService.createUser(createUserForm);
        URI uri = URI.create("/user/" + newUser.getId());
        return ResponseEntity.created(uri)
                .body(newUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        User foundUser = userService.getUser(id);
        return ResponseEntity.ok()
                .body(UserDto.builder()
                        .id(foundUser.getId())
                        .name(foundUser.getName())
                        .build());
    }

}
