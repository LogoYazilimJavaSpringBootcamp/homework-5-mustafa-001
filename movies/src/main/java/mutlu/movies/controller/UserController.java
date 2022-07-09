package mutlu.movies.controller;


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
    private UserService userService;

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

    @PostMapping("/{userId}")
    public User update(@RequestBody User request, @PathVariable Long userId) {
        return userService.update(request, userId);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
