package view.model;

import manage.users.UserDatabase;
import modules.users.User;
import sun.nio.cs.US_ASCII;

import java.util.List;


public class UserModel<T extends User> extends DataModel<T> {

    public UserModel(List<T> data) {
        super(data);
    }

}
