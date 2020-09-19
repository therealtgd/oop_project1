package manage.users;

import manage.DatabaseFactory;
import manage.FileDatabaseFactory;
import modules.users.Admin;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.users.Patient;

import java.util.ArrayList;

public class UserFileDatabaseFactory implements UserDatabaseFactory {

    private UserDatabase<Admin> adminDatabase;
    private UserDatabase<Laborant> laborantDatabase;
    private UserDatabase<MedicalTechnician> medTechnicianDatabase;
    private UserDatabase<Patient> patientDatabase;

    public UserFileDatabaseFactory(DatabaseFactory dF) {
        this.adminDatabase = dF.getAdminDatabase();
        this.laborantDatabase = dF.getLaborantDatabase();
        this.patientDatabase = dF.getPatientDatabase();
        this.medTechnicianDatabase = dF.getMedTechnicianDatabase();
    }

    public UserDatabase<Admin> getAdminDatabase() {
        return adminDatabase;
    }

    public UserDatabase<Laborant> getLaborantDatabase() {
        return laborantDatabase;
    }

    public UserDatabase<MedicalTechnician> getMedTechnicianDatabase() {
        return medTechnicianDatabase;
    }

    public UserDatabase<Patient> getPatientDatabase() {
        return patientDatabase;
    }

    @Override
    public ArrayList<UserDatabase> getUsers() {
        ArrayList<UserDatabase> retVal = new ArrayList<>();
        retVal.add(adminDatabase);
        retVal.add(laborantDatabase);
        retVal.add(medTechnicianDatabase);
        retVal.add(patientDatabase);
        return retVal;
    }

    @Override
    public void loadData() {
        this.adminDatabase.loadData();
        this.laborantDatabase.loadData();
        this.medTechnicianDatabase.loadData();
        this.patientDatabase.loadData();
    }
}
