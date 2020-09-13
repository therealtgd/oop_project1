package services.view;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.*;
import services.Services;
import view.AdminFrame;
import view.LaborantFrame;
import view.PatientFrame;
import view.validators.exceptions.LoginException;

import java.util.ArrayList;

public class LoginServices extends Services {

    private ArrayList<UserDatabase> userDatabase;

    public LoginServices() {
        super();
        this.userDatabase = getDatabaseHandler().getUserDatabase().getUsers();

    }

    public LoginServices(DatabaseHandler databaseHandler) {
        super(databaseHandler);
        this.userDatabase = getDatabaseHandler().getUserDatabase().getUsers();

    }

    public void login(String username, String password) throws LoginException{
        for (UserDatabase dB : userDatabase) {
            try {
                User u = dB.validateLogin(username, password, dB.getData());
                if (u instanceof Admin) {
                     new AdminFrame((Admin) u);
                } else if (u instanceof Laborant) {
                     new LaborantFrame((Laborant) u);
                } else if (u instanceof MedicalTechnician) {
                     new MedicalTechnicianFrame((MedicalTechnician) u);
                } else if (u instanceof Patient) {
                     new PatientFrame((Patient) u);
                }
            } catch (LoginException ignored) {
            }
        }
        throw new LoginException("Prijava neuspješna.");
    }

}
