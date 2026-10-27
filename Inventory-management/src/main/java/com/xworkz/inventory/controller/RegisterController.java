
package com.xworkz.inventory.controller;

import com.xworkz.inventory.dto.LoginDto;
import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


    // Process login form
    @PostMapping("/login")
    public String login(LoginDto loginDto, Model model) {
        System.out.println("Login method is called: " + loginDto);
        RegisterDTO dto = registerService.checkUserBylogin(loginDto);
        System.out.println("DTO: " + dto);

        if (dto != null) {
            model.addAttribute("registerDto", dto);
            return "dashboard";
        }

        model.addAttribute("msg", "Login Failed. Please check your email and password.");
        model.addAttribute("loginDto", loginDto);

        return "login";
    }


    @GetMapping("/edit")
    public String editProfile(@RequestParam("email") String email, Model model) {
        System.out.println("Edit method is called: " + email);
        RegisterDTO dto = registerService.findUserByEmail(email);

        if (dto == null) {
            model.addAttribute("msg", "User not found");
            return "login";
        }

        model.addAttribute("registerDto", dto);
        return "editProfile";
    }

    @PostMapping("/update")
    public String updateProfile(RegisterDTO registerDTO, Model model) {
        System.out.println("Update method is called: " + registerDTO);
        boolean updated = registerService.updateProfile(registerDTO);

        if (updated) {
            RegisterDTO dto = registerService.findUserByEmail(registerDTO.getEmail());

            model.addAttribute("registerDto", dto);
            return "dashboard";
        }

        model.addAttribute("registerDto", registerDTO);
        model.addAttribute("msg", "Profile update failed");
        return "editProfile";
    }

}
