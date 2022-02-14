package com.security.controller;

import com.security.dto.response.ResponseMessage;
import com.security.model.Classes;
import com.security.model.Tuition;
import com.security.model.User;
import com.security.service.impl.ClassesServiceImpl;
import com.security.service.impl.TuitionServiceImpl;
import com.security.service.impl.UserServiceImpl;
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
    UserServiceImpl userService;

    @Autowired
    ClassesServiceImpl classesService;

    @Autowired
    TuitionServiceImpl tuitionService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> users = userService.findById(id);
        if (!users.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteById(users.get().getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }


    //    coach
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


}
