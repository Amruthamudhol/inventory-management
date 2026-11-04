
package com.xworkz.inventory.controller;

import com.xworkz.inventory.dto.LoginDto;
import com.xworkz.inventory.dto.RegisterDTO;
import com.xworkz.inventory.service.RegisterService;
import com.xworkz.inventory.util.InventoryUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
@Slf4j
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    public RegisterController() {
        log.info("Created RegisterController()");
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }


    // Display registration page
    @GetMapping({"", "/"})
    public String showRegisterPage(Model model) {
        model.addAttribute("RegisterDto", new RegisterDTO());
        return "Register";
    }

    // Process registration form
    @PostMapping("/saveRegisterForm")
    public String saveRegisterForm(RegisterDTO registerDTO, Model model) {

        log.info("Running saveRegisterForm()");
        log.info("RegisterDTO: " + registerDTO);

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

    @GetMapping("/loginPage")
    public String showLoginPage(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    // Process login form
    @PostMapping("/login")
    public String login(LoginDto loginDto, Model model) {
        log.info("Login method is called: " + loginDto);
        RegisterDTO dto = registerService.checkUserBylogin(loginDto);
        log.info("DTO: " + dto);

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
        log.info("Edit method is called: " + email);
        RegisterDTO dto = registerService.findUserByEmail(email);

        if (dto == null) {
            model.addAttribute("msg", "User not found");
            return "login";
        }

        model.addAttribute("registerDto", dto);
        return "editProfile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute RegisterDTO registerDTO, Model model) {

        log.info("Form Data: " + registerDTO);
        boolean updated = registerService.updateProfile(registerDTO);

        if (updated) {

            RegisterDTO updatedDTO = registerService.findUserByEmail(registerDTO.getEmail());

            model.addAttribute("registerDto", updatedDTO);
            model.addAttribute("msg", "Profile updated successfully!");

            return "dashboard";
        }

        model.addAttribute("registerDto", registerDTO);
        model.addAttribute("msg", "Profile update failed!");

        return "editProfile";
    }

    @GetMapping("/displayImage")
    public void displayImage(@RequestParam("imageName") String imageName, HttpServletResponse response) throws IOException {

        log.info("Image name received: " + imageName);
        File file = new File(InventoryUtil.IMAGE_PATH + imageName);

        log.info("Image path: " + file.getAbsolutePath());
        log.info("File exists: " + file.exists());

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        response.setContentType("image/jpeg");

        try (InputStream inputStream = new FileInputStream(file);
             OutputStream outputStream = response.getOutputStream()) {

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }

    @GetMapping("/userLogin")
    public String userLogin() {
        return "emailLogin";
    }

    @PostMapping("/emailLogin")
    public String emailLogin(@RequestParam("email") String email, Model model) {

        log.info("Email to login: " + email);

        Boolean isSent = registerService.sendEmail(email);

        if (isSent) {
           log.info("Controller otp sent");
            model.addAttribute("email", email);
            return "Otp";
        } else {
            log.error("Controller not otp sent");
            model.addAttribute("msg", "Email not found");
            return "emailLogin";
        }
    }
    @PostMapping("/validateOtp")
    public String validateOtp(@RequestParam("email") String email, @RequestParam("otp") String otp, Model model) {

        log.info("Otp to validate: " + otp);

        RegisterDTO registerDTO = registerService.validateOtp(email, otp);

        if (registerDTO != null) {
            model.addAttribute("registerDto", registerDTO);
            return "dashboard";
        } else {
            model.addAttribute("msg", "Invalid OTP");
            model.addAttribute("email", email);
            return "Otp";
        }
    }
}
