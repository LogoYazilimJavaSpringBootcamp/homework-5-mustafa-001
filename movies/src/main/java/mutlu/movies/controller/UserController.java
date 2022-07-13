package mutlu.movies.controller;


import mutlu.movies.dto.*;
import mutlu.movies.entity.User;
import mutlu.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public Optional<User> getById(@PathVariable Long userId) {
        return userService.getByUserId(userId);
    }

    @PostMapping
    public User add(@RequestBody User request) {
        return userService.create(request);
    }

    @PostMapping("/login")
    public User login(@RequestBody LoginCredentialsDto creditenalsDto){
        return userService.login(creditenalsDto);
    }

    @PostMapping("/changeUsername")
        public User changeUsername(@RequestBody ChangeUsernameDto changeUsernameDto){
        return userService.changeUsername(changeUsernameDto);
    }
    @PostMapping("/changePassword")
    public User changePassword(@RequestBody ChangePasswordDto changePasswordDto){
        return userService.changePassword(changePasswordDto);
    }


    @PostMapping("/{userId}")
    public User update(@RequestBody User request, @PathVariable Long userId) {
        return userService.update(request, userId);
    }
    @PostMapping("/payment")
    public User makePayment(@RequestBody PaymentDto paymentDetailsDto){
        return userService.makePayment(paymentDetailsDto);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
