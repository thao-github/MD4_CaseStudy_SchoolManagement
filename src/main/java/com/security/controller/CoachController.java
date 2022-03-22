package com.security.controller;


import com.security.model.Role;
import com.security.model.RoleName;
import com.security.model.User;
import com.security.service.impl.RoleServiceImpl;
import com.security.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/coaches")
public class CoachController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;


    @GetMapping
    public ResponseEntity<List<User>> show(@RequestParam(defaultValue = "0") int start) {
        return new ResponseEntity<>(userService.findAllCoach(start), HttpStatus.OK);
    }

    @GetMapping("/count")
    public int countAllCoach() {
        return userService.countAllCoach();
    }

    @GetMapping("/create")
    public User create(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        Role coachRole = roleService.findByName(RoleName.COACH).orElseThrow(
                () -> new RuntimeException("Role not found!")
        );
        roles.add(coachRole);
        user.setRoles(roles);
        if(user.getAvatar() == null) {
            user.setAvatar("https://firebasestorage.googleapis.com/v0/b/hatsales2.appspot.com/o/user.png?alt=media&token=760eb9f2-72e2-41d0-88d6-d2808a7b5362");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }


    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> edit(@PathVariable Long id, @RequestBody User users) {
        Optional<User> oldUser = userService.findById(id);
        if (users.getPassword() == null || users.getAvatar() == null) {
            users.setPassword(oldUser.get().getPassword());
        }
        if (users.getAvatar() == null) {
            users.setAvatar(oldUser.get().getAvatar());
        }
        users.setId(id);
        userService.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("search/{name}")
    public ResponseEntity<List<User>> search(@PathVariable String name, @RequestParam(defaultValue = "0") int start) {
        return new ResponseEntity<>(userService.searchCoach(name, start), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
