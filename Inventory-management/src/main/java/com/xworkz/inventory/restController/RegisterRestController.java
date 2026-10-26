package com.xworkz.inventory.restController;

import com.xworkz.inventory.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class RegisterRestController {

    private RegisterService service;

    @GetMapping("/checkEmailExit/{email:.+}")
    public String checkEmailExit(@PathVariable("email") String email) {
        System.out.println("Controller email: [" + email + "]");
        return service.findEmail(email);
    }

    @GetMapping("/checkPhoneNumber/{phoneNumber:.+}")
    public String checkPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {

        System.out.println("Invoking checkPhoneNumber: " + phoneNumber);

        return service.findPhoneNumber(phoneNumber);
    }
}
