package com.security.controller;

import com.security.dto.request.SignInForm;
import com.security.dto.request.SignUpForm;
import com.security.dto.response.JwtResponse;
import com.security.dto.response.ResponseMessage;
import com.security.model.Role;
import com.security.model.RoleName;
import com.security.model.User;
import com.security.security.jwt.JwtProvider;
import com.security.security.userprincal.UserPrinciple;
import com.security.service.impl.RoleServiceImpl;
import com.security.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpForm signupForm) {
        if (userService.existsByUsername(signupForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Username existed!"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signupForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email existed!"), HttpStatus.OK);
        }
        if (signupForm.getAvatar() ==null || signupForm.getAvatar().trim().isEmpty()){
            signupForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/casestudymd4-school.appspot.com/o/students.png?alt=media&token=83207a05-5989-4653-b845-88d702aa10c5");
        }
        User user = new User(signupForm.getName(), signupForm.getUsername(), signupForm.getEmail(), signupForm.getAvatar(),passwordEncoder.encode(signupForm.getPassword()));
        Set<String> strRole = signupForm.getRoles();
        Set<Role> roles = new HashSet<>();
        strRole.forEach((role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN).orElseThrow(
                            () -> new RuntimeException("Role not found!")
                    );
                    roles.add(adminRole);
                    break;
                case "coach":
                    Role coachRole = roleService.findByName(RoleName.COACH).orElseThrow(
                            () -> new RuntimeException("Role not found!")
                    );
                    roles.add(coachRole);
                    break;
                default:
                    Role studentRole = roleService.findByName(RoleName.STUDENT).orElseThrow(
                            () -> new RuntimeException("Role not found!")
                    );
                    roles.add(studentRole);
            }
        }));
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Create success!"), HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?>login(@Valid @RequestBody SignInForm signInForm){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUsername(), signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User user = userService.findByUsername(userPrinciple.getUsername()).get();
        return ResponseEntity.ok(new JwtResponse(token, user));
    }
}
