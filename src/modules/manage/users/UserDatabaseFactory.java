package modules.manage.users;

import modules.manage.Database;

public interface UserDatabaseFactory {

    Database getAdminDatabase();
    Database getLaborantDatabase();
    Database getMedTechnicianDatabase();
    Database getPatientDatabase();
    void loadData();

}
