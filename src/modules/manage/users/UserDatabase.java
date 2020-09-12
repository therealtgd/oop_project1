package modules.manage.users;

import modules.manage.Database;

public interface UserDatabase {

    Database getAdminDatabase();

    Database getLaborantDatabase();

    Database getMedTechnicianDatabase();

    Database getPatientDatabase();

}
