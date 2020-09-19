package manage;


import manage.entities.EntityFileDatabaseFactory;
import manage.users.*;
import modules.entities.*;
import modules.users.Admin;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.users.Patient;
import modules.utils.AppSettings;

public class DatabaseHandler {

    private DatabaseFactory databaseFactory;
    private UserDatabaseFactory userDatabase;
    private EntityDatabaseFactory entityDatabase;

    public DatabaseHandler() {
         AppSettings aS = new AppSettings("data/users/admin.csv",
                "data/users/laborant.csv",
                "data/users/medicalTechnician.csv",
                "data/users/patient.csv",
                "data/entites/analysis.csv",
                "data/entites/analysisGroup.csv",
                "data/entites/measurement.csv",
                "data/entites/analysisRequest.csv",
                 "data/entites/notification.csv");
         this.databaseFactory = new DataFileDatabaseFactory(aS);
         this.userDatabase = new UserFileDatabaseFactory(databaseFactory);
         this.entityDatabase = new EntityFileDatabaseFactory(databaseFactory);

    }

    public DatabaseHandler(UserDatabaseFactory userDatabase, EntityDatabaseFactory entityManagerFactory) {
        this.userDatabase = userDatabase;
        this.entityDatabase = entityManagerFactory;
    }

    public UserDatabaseFactory getUserDatabase() { return userDatabase; }


    public EntityDatabaseFactory getEntityDatabase() {
        return entityDatabase;
    }
}
