package koff.expense.controller;

import koff.expense.model.dto.user.UserDTO;
import koff.expense.model.form.user.UserForm;
import koff.expense.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Log4j2
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/insert")
    public void insertUser(@RequestBody UserForm userForm) {
        log.info("UserForm: {}", userForm);
        userService.insertUser(userForm);
    }

    @GetMapping()
    public UserDTO getUserForm(@RequestBody String username) {
        return userService.getUserByUsername(username);
    }

}
