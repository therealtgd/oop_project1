package services.view;

import manage.users.UserDatabase;
import modules.DTO.PatientAccountDTO;
import modules.users.Patient;
import modules.users.User;
import services.Services;
import services.utils.Builder;
import services.view.exceptions.RegistrationException;

import java.util.ArrayList;

public class RegistrationServices extends Services {

    private ArrayList<UserDatabase> userDatabase;

    public RegistrationServices() {
        super();
        this.userDatabase = getDatabaseHandler().getUserDatabase().getUsers();
    }

    public void register(PatientAccountDTO pDTO) throws RegistrationException {
        for (UserDatabase<User> uDb: userDatabase) {
            for (User u: uDb.getData()) {
                if (u.getUsername().equals(pDTO.getUsername())) {
                    throw new RegistrationException("Korisničko ime je zauzeto.\nPokušajte ponovo.");
                }
            }
        }
        Patient p = Builder.buildPatient(pDTO);
        getDatabaseHandler().getUserDatabase().getPatientDatabase().addData(p);
    }


}
