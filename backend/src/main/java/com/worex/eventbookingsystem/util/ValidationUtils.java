package com.worex.eventbookingsystem.util;

import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor
public class ValidationUtils {

    // Regex for email
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Regex for username
    private static final String USERNAME_REGEX = "^[A-Za-z0-9]{3,30}$";
    private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_REGEX);

    // Password
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    // Validate Email
    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    // Validate Username
    public static boolean isValidUsername(String username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches();
    }

    // Validate Password
    public static boolean isValidPassword(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    // Validate phone
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) return true;

        return phone.matches("\\d{7,15}");
    }
}
