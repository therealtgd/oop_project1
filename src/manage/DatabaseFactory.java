package manage;

import manage.users.UserDatabase;
import modules.entities.*;
import modules.users.Admin;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.users.Patient;

import java.util.ArrayList;

public interface DatabaseFactory {

    UserDatabase<Admin> getAdminDatabase();

    UserDatabase<Laborant> getLaborantDatabase();

    UserDatabase<MedicalTechnician> getMedTechnicianDatabase();

    UserDatabase<Patient> getPatientDatabase();

    Database<Analysis> getAnalysisDatabase();

    Database<AnalysisGroup> getAnalysisGroupDatabase();

    Database<AnalysisRequest> getAnalysisRequestDatabase();

    Database<Measurement> getMeasurementDatabase();

    Database<AnalysisRequestNotification> getAnalysisRequestNotificationDatabase();

    ArrayList<UserDatabase> getUsers();

    void loadData();


}
