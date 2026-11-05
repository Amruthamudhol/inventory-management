package com.xworkz.inventory.util;


import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class InventoryUtil {
    public static final String IMAGE_PATH = "C:/Users/HP/OneDrive/Desktop/inventory/images/";


    public static String generateOtp() {
        SecureRandom random = new SecureRandom();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }


    public static String generateResetToken() {
        byte[] tokenBytes = new byte[32];
        new SecureRandom().nextBytes(tokenBytes);

        StringBuilder token = new StringBuilder();

        for (byte b : tokenBytes) {
            token.append(String.format("%02x", b));
        }

        return token.toString();
    }
}
