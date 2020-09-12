package services.view;

import modules.manage.DatabaseHandler;
import services.Services;

import java.awt.*;

public class LoginServices extends Services {
    public LoginServices(DatabaseHandler databaseHandler) {
        super(databaseHandler);
    }

    public Frame login(String username, String password) {
        List<Patient> p = getDatabaseHandler().getUserDatabase().get
    }

}
