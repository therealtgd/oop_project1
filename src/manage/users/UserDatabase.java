package manage.users;

import manage.Database;
import modules.users.User;
import view.validators.exceptions.LoginException;

import java.util.List;

public interface UserDatabase<T extends User> extends Database<T> {

    T validateLogin(String username, String password, List<T> data) throws LoginException;

}
