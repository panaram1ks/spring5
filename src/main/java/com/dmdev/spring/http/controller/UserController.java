package com.dmdev.spring.http.controller;

import com.dmdev.spring.dto.UserCreateEditDto;
import com.dmdev.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public String findAll(Model model) {
//        model.addAttribute("users", userService.findAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String findAll(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.findById(id));
        return "user/user";
    }

    @PostMapping
    public String create(@ModelAttribute UserCreateEditDto user) {
//        userService.create(user);
        return "redirect:/users/" + 25;
    }

    //    @PutMapping("/{id}")
    @PostMapping("/{id}/update")
    public String update(@ModelAttribute UserCreateEditDto user, @PathVariable("id") Long id) {
//        userService.update(id, user);
        return "redirect:/users/{id}";
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
//        userService.delete(id);
        return "redirect:/users";
    }

}
