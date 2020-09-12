package services.user;

import modules.DTO.PatientAccountDTO;
import modules.manage.DatabaseHandler;
import modules.users.Patient;
import services.Services;
import services.utils.Builder;

public class MedicalTechnicianServices extends Services {


    public MedicalTechnicianServices(DatabaseHandler databaseHandler) {
        super(databaseHandler);
    }

    public boolean registerPatient(PatientAccountDTO pDTO) {
        Patient p = Builder.buildPatient(pDTO);
        getDatabaseHandler().getUserDatabase().getPatientDatabase().addData(p);
        return true;
    }

}



