package com.xworkz.inventory.restController;

import com.xworkz.inventory.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class RegisterRestController {

    private RegisterService service;

    @GetMapping("/checkEmailExit")
    public String checkEmailExit(@RequestParam("email") String email) {

        System.out.println("Controller received: [" + email + "]");
        System.out.println("Email length: " + email.length());

        return service.findEmail(email);
    }

    @GetMapping("/checkPhoneNumber")
    public String checkPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {

        System.out.println("Invoking checkPhoneNumber: " + phoneNumber);

        return service.findPhoneNumber(phoneNumber);
    }
}
