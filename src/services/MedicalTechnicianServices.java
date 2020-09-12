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

class MedicalTechnicianServicesInjector {

    public static MedicalTechnicianServices services() {
        AppSettings aS = new AppSettings("data/users/admin.csv",
                "data/users/laborant.csv",
                "data/users/medicalTehnician.csv",
                "data/users/patient.csv",
                "data/entities/analysis.csv",
                "data/entites/analysisRequest.csv",
                "data/entites/measurement.csv");

        return new MedicalTechnicianServices(new DatabaseHandler(new UserFileDatabaseFactory(aS),
                new EntityFileDatabaseFactory(aS)));
    }
}
