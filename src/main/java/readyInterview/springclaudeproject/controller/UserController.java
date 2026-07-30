package readyInterview.springclaudeproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import readyInterview.springclaudeproject.dto.UserDto;
import readyInterview.springclaudeproject.entity.User;
import readyInterview.springclaudeproject.service.NotificationProducer;
import readyInterview.springclaudeproject.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public final NotificationProducer notificationProducer;


    @Autowired
    public UserController(UserService userService, NotificationProducer notificationProducer) {
        this.userService = userService;
        this.notificationProducer = notificationProducer;
    }

    @PostMapping("/register")
    public void createUser(@RequestBody UserDto userDto) {

        userService.save(userDto);

    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/login")
    public String login(@RequestBody UserDto userDto) {

        return userService.findUser(userDto);

    }

    @GetMapping("/test-Notification")
    public String testNotification() {
        notificationProducer.sendNotification("Test message from producer");
         return "This is a test notification";
    }
}
