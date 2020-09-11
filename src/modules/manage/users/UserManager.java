package modules.manage.users;

import modules.manage.FileManager;
import modules.users.User;

public abstract class UserManager<U extends User> extends FileManager<U> {

    public UserManager() {
    }

    public UserManager(String file) {
        super(file);
    }

    public abstract boolean loadData();

}
