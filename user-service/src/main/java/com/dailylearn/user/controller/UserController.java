package com.dailylearn.user.controller;

import com.dailylearn.user.VO.ResponseTemplateVO;
import com.dailylearn.user.entity.User;
import com.dailylearn.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithDepartment(@PathVariable("id") Long userId) {
        log.info("Inside getUserWithDepartment of UserController");
        return userService.getUserWithDepartment(userId);
    }

    @GetMapping("/")
    public List<ResponseTemplateVO> getAllUsersWithDepartment() {
        log.info("Inside getAllUsersWithDepartment of UserController");
        return userService.getAllUsersWithDepartment();
    }


}
