package com.example.assignment2.user.controller;

import com.example.assignment2.user.dto.UserDTO;
import com.example.assignment2.user.dto.UserListDTO;
import com.example.assignment2.user.dto.UserMinimalDTO;
import com.example.assignment2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.hibernate.cfg.AvailableSettings.USER;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserListDTO> allUsers() {
        return userService.allUsersForList();
    }

    @PostMapping
    public UserDTO create(@RequestBody UserDTO user) {
        return userService.create(user);
    }

    @PatchMapping
    public void edit(@RequestBody UserDTO user) {
        userService.edit(user);
    }

    @DeleteMapping
    public void delete(@RequestBody UserDTO user) {
        userService.delete(user.getId());
    }
}