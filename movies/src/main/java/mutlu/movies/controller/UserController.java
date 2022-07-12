package mutlu.movies.controller;


import mutlu.movies.dto.ChangePasswordDto;
import mutlu.movies.dto.ChangeUsernameDto;
import mutlu.movies.dto.LoginCredentialsDto;
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

    @GetMapping("/{username}")
    public Optional<User> getById(@PathVariable String username) {
        return userService.getByUserId(username);
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

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable String userId) {
        userService.delete(userId);
    }
}
