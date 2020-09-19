package services.view;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.*;
import services.Services;
import view.admin.AdminFrame;
import view.laborant.LaborantFrame;
import view.patient.PatientFrame;
import view.medicalTechnician.MedicalTechnicianFrame;
import view.validators.exceptions.LoginException;

import java.util.ArrayList;

public class LoginServices extends Services {

    private ArrayList<UserDatabase> userDatabase;

    public LoginServices(DatabaseHandler dH) {
        super(dH);
        this.userDatabase = getdH().getUserDatabase().getUsers();

    }

    public void login(String username, String password) throws LoginException{
        for (UserDatabase dB : userDatabase) {
            try {
                User u = dB.validateLogin(username, password, dB.getData());
                if (u instanceof Admin) {
                     new AdminFrame((Admin) u, getdH());
                     return;
                } else if (u instanceof Laborant) {
                     new LaborantFrame((Laborant) u, getdH());
                     return;
                } else if (u instanceof MedicalTechnician) {
                     new MedicalTechnicianFrame((MedicalTechnician) u, getdH());
                     return;
                } else if (u instanceof Patient) {
                     new PatientFrame((Patient) u, getdH());
                     return;
                }
            } catch (LoginException ignored) {
            }
        }
        throw new LoginException("Prijava neuspješna.");
    }

}
