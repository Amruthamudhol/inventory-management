package com.xworkz.inventory.restController;

import com.xworkz.inventory.service.RegisterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class RegisterRestController {

    private RegisterService service;

    @GetMapping("/checkEmailExit/{email:.+}")
    public String checkEmailExit(@PathVariable("email") String email) {
        log.info("Controller email: [" + email + "]");
        return service.findEmail(email);
    }

    @GetMapping("/checkPhoneNumber/{phoneNumber:.+}")
    public String checkPhoneNumber(@PathVariable("phoneNumber") String phoneNumber) {

        log.info("Invoking checkPhoneNumber: " + phoneNumber);

        return service.findPhoneNumber(phoneNumber);
    }


    @GetMapping("/checkLoginEmail/{email:.+}")
    public String checkLoginEmail(@PathVariable("email") String email) {

        log.info("Checking login email: [" + email + "]");
        return service.findEmail(email);
    }
}
