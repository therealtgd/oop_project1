package view.validators;

import view.validators.exceptions.LoginException;

public class Validator {

    public static void validateLogin(String username, String password) throws LoginException {
        if (username.isEmpty()) {
            throw new LoginException("Unesite korisničko ime.");
        } else if (password.isEmpty()) {
            throw new LoginException("Unesite šifru.");
        }
    }

}
