package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User createNewUser(@RequestBody User RqUser){
        User newUser= this.userService.setNewUser(RqUser);
        return newUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable long id){
        this.userService.deleteUserById(id);
        return "user deleted";
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable long id) {
        User user = this.userService.getUserById(id);
        return user;
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        List<User> users = this.userService.getAllUsers();
        return users;
    }

    @PutMapping("user")
    public User updateUser(@RequestBody User updatedUser) {
        User user = this.userService.setNewUser(updatedUser);
        return user;
    }

}
