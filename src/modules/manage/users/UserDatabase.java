package modules.manage.users;

import modules.manage.Database;
import modules.users.User;

public interface UserDatabase {

    User validateLogin(String username, String password);

}
