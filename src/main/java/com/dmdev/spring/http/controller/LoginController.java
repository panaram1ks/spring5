package com.dmdev.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

  @GetMapping("/login")
  public String loginPage(){
        return "user/login";
  }


  //POST MAPPING PROVIDE Security!!
//    @PostMapping("/login")
//    public String login(Model model , @ModelAttribute("customName") LoginDto loginDto){
////        return "forward:/WEB-INF/jsp/user/login.html"; // FORWARD example
//        return "redirect:/login"; // REDIRECT example
//    }

}
