package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    TestService testService;

    @GetMapping("/login")
    public String showForm(Model model) {
        return "login";
    }

    @PostMapping(value="/login")
    public String processForm(@RequestParam String action){

        if( action.equals("save") ){
            return "redirect:/";
        }
        else {
            return "redirect:/";
        }
    }
}
