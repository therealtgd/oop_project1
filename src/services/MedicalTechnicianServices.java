package services;

import modules.DTO.PatientAccountDTO;
import modules.manage.DatabaseHandler;
import modules.manage.entities.EntityFileDatabaseFactory;
import modules.manage.users.UserFileDatabaseFactory;
import modules.users.Patient;
import modules.utils.AppSettings;
import services.utils.Builder;

public class MedicalTechnicianServices {

    private DatabaseHandler databaseHandler;

    public MedicalTechnicianServices(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public boolean registerPatient(PatientAccountDTO pDTO) {
        Patient p = Builder.buildPatient(pDTO);
        databaseHandler.getUserDatabase().getPatientDatabase().addData(p);
        return true;
    }

}

