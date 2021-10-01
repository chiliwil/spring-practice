package com.proyecto.test.modules.user;

import com.proyecto.test.common.exception.ResourceNotFoundException;
import com.proyecto.test.modules.user.dto.CreateUserDto;
import com.proyecto.test.modules.user.dto.UpdateUserDto;
import com.proyecto.test.modules.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping(value = "user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    private ResponseEntity<?> createUserController(@Valid @RequestBody CreateUserDto user) throws Exception {
        UserEntity userEntity = userService.createUser(user);
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping
    private ResponseEntity<?> getAllUser() throws ResourceNotFoundException {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getById(@PathVariable("id") String id) throws ResourceNotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    private ResponseEntity<?> updateUser(@PathVariable("id") String id, @Valid @RequestBody UpdateUserDto user) throws Exception {
        Optional<UserEntity> userEntity = userService.updateUser(id, user);
        return ResponseEntity.ok(userEntity);
    }
}
