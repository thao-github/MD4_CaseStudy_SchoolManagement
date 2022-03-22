package com.security.controller;

import com.security.dto.response.ResponseMessage;
import com.security.model.Classes;
import com.security.model.Mark;
import com.security.model.Tuition;
import com.security.model.User;
import com.security.service.IClassesService;
import com.security.service.IMarkService;
import com.security.service.ITuitionService;
import com.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("user")
@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IClassesService classesService;

    @Autowired
    ITuitionService tuitionService;

    @Autowired
    IMarkService markService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> users = userService.findById(id);
        if (!users.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(users.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }
    //    coach- class
    @GetMapping("/classes/show")
    public ResponseEntity<List<Classes>> showAll() {
        return new ResponseEntity<>(classesService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/classes/count")
    public Long countProduct() {
        return classesService.countClasses();
    }

    @GetMapping("/classes/search/{name}")
    public List<Classes> findByName(@PathVariable String name) {
        return classesService.findAllByName(name);
    }

    //    coach- student
    @GetMapping("/coach/students/{id}")
    public List<User> findAllStudentByCoach(@PathVariable Long id) {
        return userService.findAllStudentByCoach(id);
    }

    @GetMapping("coach/students/{id}/search/{name}")
    public List<User> findAllStudentByName(@PathVariable(value = "id") Long id, @PathVariable(value = "name") String name) {
        return userService.findAllStudentByName(id, name);
    }

    @GetMapping("/coach/students/{id}/{studentId}")
    public User getStudentInfo(@PathVariable(value = "id") Long classId, @PathVariable(value = "studentId") Long studentId) {
        return userService.findUserById(classId, studentId);
    }

    @GetMapping("/coach/{id}/{status}")
    public List<User> findUserByStatus(@PathVariable(value = "id") Long id, @PathVariable(value = "status") String status) {
        return userService.findUserByStatus(id, status);
    }

    //    student
    @GetMapping("/student/tuition/{id}")
    public List<Tuition> getTuition(@PathVariable(value = "id") Long id) {
        return tuitionService.getTuitionHistory(id);
    }

    @GetMapping("/student/mark/{id}")
    public List<Mark> getStudentMark(@PathVariable Long id){
        return markService.getStudentMark(id);
    }

}
