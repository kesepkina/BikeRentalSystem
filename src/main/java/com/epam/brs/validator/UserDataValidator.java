package com.epam.brs.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {

    private static final String LOGIN_KEY = "login";
    private static final String EMAIL_KEY = "email";
    private static final String PASSWORD_KEY = "password";
    private static final String INCORRECT_VALUE = " INCORRECT";
    private static final Pattern LOGIN_PATTERN = Pattern.compile("[a-zA-Z0-9._]{5,20}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,7}$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&()])(?=\\S+).{8,20}$");

    private UserDataValidator() {
    }

    public static boolean isLogin(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) return false;
        Matcher matcher = LOGIN_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }

    public static boolean isPassword(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) return false;
        Matcher matcher = PASSWORD_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }

    public static boolean isEmail(String inputtedData) {
        if (inputtedData == null || inputtedData.isBlank()) return false;
        Matcher matcher = EMAIL_PATTERN.matcher(inputtedData);
        return matcher.matches();
    }

    public static boolean isValidForm(Map<String, String> form) {
        boolean valid = true;
        String loginValue = form.get(LOGIN_KEY);
        if (!isLogin(loginValue)) {
            form.put(LOGIN_KEY, loginValue + INCORRECT_VALUE);
            valid = false;
        }
        String emailValue = form.get(EMAIL_KEY);
        if (!isEmail(emailValue)) {
            form.put(EMAIL_KEY, emailValue + INCORRECT_VALUE);
            valid = false;
        }
        String passwordValue = form.get(PASSWORD_KEY);
        if (!isPassword(passwordValue)) {
            form.put(PASSWORD_KEY, passwordValue + INCORRECT_VALUE);
            valid = false;
        }
        return valid;
    }
}
