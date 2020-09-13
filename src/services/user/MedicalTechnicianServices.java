package services.user;

import modules.DTO.PatientAccountDTO;
import manage.DatabaseHandler;
import modules.users.Patient;
import services.Services;
import services.utils.Builder;

import static services.utils.PasswordUtils.generateRandomAlphanumericString;

public class MedicalTechnicianServices extends Services {


    public MedicalTechnicianServices(DatabaseHandler databaseHandler) {
        super(databaseHandler);
    }

    public String registerPatient(PatientAccountDTO pDTO) {
        pDTO.setPassword(generateRandomAlphanumericString(10));
        Patient p = Builder.buildPatient(pDTO);
        getDatabaseHandler().getUserDatabase().getPatientDatabase().addData(p);
        return pDTO.getPassword();
    }

}



