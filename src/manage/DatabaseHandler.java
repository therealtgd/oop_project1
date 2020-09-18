package manage;


import manage.entities.EntityFileDatabaseFactory;
import manage.users.UserDatabaseFactory;
import manage.users.UserFileDatabaseFactory;
import modules.utils.AppSettings;

public class DatabaseHandler {

    private UserDatabaseFactory userDatabase;
    private EntityDatabaseFactory entityDatabase;

    public DatabaseHandler() {
         AppSettings aS = new AppSettings("data/users/admin.csv",
                "data/users/laborant.csv",
                "data/users/medicalTehnician.csv",
                "data/users/patient.csv",
                "data/entites/analysis.csv",
                "data/entites/analysisGroup.csv",
                "data/entites/measurement.csv",
                "data/entites/analysisRequest.csv",
                 "data/entites/notification.csv");
         this.userDatabase = new UserFileDatabaseFactory(aS);
         this.entityDatabase = new EntityFileDatabaseFactory(aS);

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
