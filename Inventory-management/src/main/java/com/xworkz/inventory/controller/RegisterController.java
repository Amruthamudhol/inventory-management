package com.xworkz.inventory.controller;

import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    public RegisterController() {
        System.out.println("Created RegisterController()");
    }

    @GetMapping("/saveRegisterForm")
    public String saveRegisterForm(Model model) {
        System.out.println("Running saveRegisterForm() in RegisterController");
        model.addAttribute("RegisterDto", new RegisterDTO());
        return "Register";
    }



    @PostMapping("/saveRegisterForm")
    public String saveRegisterDetails(@Valid @ModelAttribute("RegisterDto") RegisterDTO registerDTO, BindingResult bindingResult, Model model) {

        System.out.println("Running saveRegisterDetails() in RegisterController");

        if (bindingResult.hasErrors()) {System.out.println("There are validation errors, please fix them");
            List<ObjectError> errors = bindingResult.getAllErrors();
            model.addAttribute("validationErrors", errors);
            return "Register";
        } else {
            System.out.println("No validation errors, saving the register details");

            this.registerService.save(registerDTO);
            model.addAttribute("successMessage", "Register details saved successfully");
            model.addAttribute("RegisterDto", new RegisterDTO());

            return "Register";
        }
    }

}