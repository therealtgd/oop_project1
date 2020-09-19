package view.model;

import manage.users.UserDatabase;
import modules.users.User;


public class UserModel extends DataModel {

    public UserModel(UserDatabase<User> userDatabase) {
        super(userDatabase);
    }

}
