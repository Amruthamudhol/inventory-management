
package com.xworkz.inventory.controller;

import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    public RegisterController() {
        System.out.println("Created RegisterController()");
    }

    // Display registration page
    @GetMapping({"", "/"})
    public String getRegisterPage(Model model) {
        System.out.println("Running getRegisterPage()");
        model.addAttribute("RegisterDto", new RegisterDTO());
        return "Register";
    }

    // Process registration form
    @PostMapping("/saveRegisterForm")
    public String saveRegisterForm(RegisterDTO registerDTO, Model model) {

        System.out.println("Running saveRegisterForm()");
        System.out.println("RegisterDTO: " + registerDTO);

        String message = registerService.save(registerDTO);

        if ("Registration Successful".equals(message)) {
            model.addAttribute("successMessage", message);
            model.addAttribute("RegisterDto", new RegisterDTO());
        } else {
            model.addAttribute("errorMessage", message);
            model.addAttribute("RegisterDto", registerDTO);
        }

        return "Register";
    }
}
