package manage.users;

import modules.users.*;

import java.util.ArrayList;


public interface UserDatabaseFactory {

    UserDatabase<Admin> getAdminDatabase();

    UserDatabase<Laborant> getLaborantDatabase();

    UserDatabase<MedicalTechnician> getMedTechnicianDatabase();

    UserDatabase<Patient> getPatientDatabase();

    ArrayList<UserDatabase> getUsers();

    void loadData();

}
