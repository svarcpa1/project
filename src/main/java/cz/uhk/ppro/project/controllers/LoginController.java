package cz.uhk.ppro.project.controllers;

import cz.uhk.ppro.project.services.TestService;
import cz.uhk.ppro.project.springSecurity.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    TestService testService;

    @GetMapping("/login")
    public String showForm(Model model) {
        return "login";
    }

    @GetMapping("/loginFailed")
    public String showFailed(Model model) {
        model.addAttribute("error", true);
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User loggedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loggedUser);
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "403";
    }

}
