package tracker;

import java.util.Arrays;

public class Validator {

    static boolean isValidCredentials(String[] args) {
        return args.length >= 3;
    }

    static boolean isValidName(String name) {
        return name.matches("(?i)^([a-z]['-]?)+[a-z]$");
    }

    static boolean isValidMail(String email) {
        return email.matches("(?i)^[a-z0-9._-]+@[a-z0-9_-]+\\.([a-z0-9]\\.?)+$");
    }

    static boolean isValidPointsFormat(String[] idPointsInput) {
        return idPointsInput.length == 5
                && Arrays.stream(idPointsInput).skip(1).allMatch(s -> s.matches("\\d+"));
    }
}
