
package com.security.controller;
import com.security.model.User;
import com.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UsersController {
    @Autowired
    IUserService userService;

//    @GetMapping
//    public ResponseEntity<Page<Users>> show(@RequestParam(defaultValue = "0") int page) {
//        return new ResponseEntity<>(userService.findAll(PageRequest.of(page, 3)), HttpStatus.OK);
//    }

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
        if(users.getAvatar() == null) {
            users.setAvatar(oldUser.get().getAvatar());
        }
        users.setRoles(oldUser.get().getRoles());
        users.setClasses(oldUser.get().getClasses());
        users.setId(id);
        userService.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

//
//    @DeleteMapping("/{id}")
//    public ResponseEntity delete(@PathVariable Long id) {
//        userService.deleteById(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }
//
//    @GetMapping("/countUsers")
//    public int countUsers() {
//        return userService.countUsers();
//    }

}