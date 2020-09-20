package services.user;

import modules.DTO.PatientDTO;
import manage.DatabaseHandler;
import modules.users.MedicalTechnician;
import modules.users.Patient;
import services.Services;
import services.utils.Builder;

import static services.utils.PasswordUtils.generateRandomAlphanumericString;

public class MedicalTechnicianServices extends Services {


    public MedicalTechnicianServices(DatabaseHandler databaseHandler) {
        super(databaseHandler);
    }

    public String registerPatient(PatientDTO pDTO) {
        pDTO.setPassword(generateRandomAlphanumericString(10));
        Patient p = Builder.buildPatient(pDTO);
        getdH().getUserDatabase().getPatientDatabase().addData(p);
        return pDTO.getPassword();
    }

    public void incrementHomeVisit(MedicalTechnician mT) {
        getdH().getUserDatabase().getMedTechnicianDatabase().getById(mT.getId()).incrementHomeVisit();
        getdH().getUserDatabase().getMedTechnicianDatabase().saveData();
    }
}



