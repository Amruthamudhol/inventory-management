package com.xworkz.inventory.restController;

import com.xworkz.inventory.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class RegisterRestController {

    private RegisterService service;

    @GetMapping("/checkEmailExit")
    public String checkEmailExit(@RequestParam("email") String email) {

        System.out.println("Invoking checkEmailExit: " + email);

        return service.findEmail(email);
    }
}
