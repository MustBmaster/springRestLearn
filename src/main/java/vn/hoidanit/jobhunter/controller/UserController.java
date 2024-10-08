package vn.hoidanit.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.util.error.IdInvalidException;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User RqUser) {
        //logic đoạn mã hóa mật khẩu như sau
        String hashPass= this.passwordEncoder.encode(RqUser.getPassword());//lấy ra pass word của user trong request rồi encode nó
        RqUser.setPassword(hashPass);//rồi ghi đè lên mk cũ
        User newUser = this.userService.setNewUser(RqUser);//rồi mới lưu vào db
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);//và trả lại kết quả
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) throws IdInvalidException {
        if(id >1111){
            throw new IdInvalidException("ID must be less than 1112");
        }
        // hoặc viết kiểu ngắn hơn
        this.userService.deleteUserById(id);
        // return ResponseEntity.status(HttpStatus.OK).body("Deleted");
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id) {
        User user = this.userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        List<User> users = this.userService.getAllUsers();
        return users;
    }

    @PutMapping("/users")
    public User updateUser(@RequestBody User updatedUser) {
        User user = this.userService.updateUser(updatedUser);
        return user;
    }

}
