package services;

import modules.manage.DatabaseHandler;
import modules.manage.entities.EntityFileDatabaseFactory;
import modules.manage.users.UserFileDatabaseFactory;
import modules.utils.AppSettings;

public class MedicalTechnicianServicesInjector {

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