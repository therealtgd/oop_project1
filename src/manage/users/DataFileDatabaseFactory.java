package manage.users;

import manage.Database;
import manage.DatabaseFactory;
import manage.FileDatabaseFactory;
import manage.entities.*;
import modules.entities.*;
import modules.users.Admin;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.users.Patient;
import modules.utils.AppSettings;

import java.util.ArrayList;

public class DataFileDatabaseFactory extends FileDatabaseFactory implements DatabaseFactory {

    private UserDatabase<Admin> adminDatabase;
    private UserDatabase<Laborant> laborantDatabase;
    private UserDatabase<MedicalTechnician> medTechnicianDatabase;
    private UserDatabase<Patient> patientDatabase;

    private Database<Analysis> analysisDatabase;
    private Database<AnalysisGroup> analysisGroupDatabase;
    private Database<Measurement> measurementDatabase;
    private Database<AnalysisRequest> analysisRequestDatabase;
    private Database<AnalysisRequestNotification> analysisRequestNotificationDatabase;


    public DataFileDatabaseFactory(AppSettings appSettings) {
        super(appSettings);
        this.adminDatabase = new AdminFileDatabase(getAppSettings().getAdminFilename());
        this.laborantDatabase = new LaborantFileDatabase(getAppSettings().getLaborantFilename());
        this.patientDatabase = new PatientFileDatabase(getAppSettings().getPatientFilename());

        this.measurementDatabase = new MeasurementFileDatabase(getAppSettings().getMeasurementFilename());
        this.analysisDatabase = new AnalysisFileDatabase(getAppSettings().getAnalysisFilename(), measurementDatabase);
        this.analysisGroupDatabase = new AnalysisGroupFileDatabase(getAppSettings().getAnalysisGroupFilename(), analysisDatabase);
        this.analysisRequestDatabase = new AnalysisRequestFileDatabase(getAppSettings().getAnalysisRequestFilename(),
                analysisDatabase, patientDatabase, measurementDatabase);
        this.analysisRequestNotificationDatabase = new AnalysisRequestNotificationFileDatabase(getAppSettings().getNotificationFilename(), analysisRequestDatabase);
        this.medTechnicianDatabase = new MedicalTechnicianFileDatabase(getAppSettings().getMedicalTechnicianFilename(), analysisRequestNotificationDatabase);


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

    public Database<Analysis> getAnalysisDatabase() {
        return analysisDatabase;
    }

    public Database<AnalysisGroup> getAnalysisGroupDatabase() {
        return analysisGroupDatabase;
    }

    public Database<AnalysisRequest> getAnalysisRequestDatabase() {
        return analysisRequestDatabase;
    }

    public Database<Measurement> getMeasurementDatabase() {
        return measurementDatabase;
    }

    public Database<AnalysisRequestNotification> getAnalysisRequestNotificationDatabase() {
        return analysisRequestNotificationDatabase;
    }

    public ArrayList<UserDatabase> getUsers() {
        ArrayList<UserDatabase> retVal = new ArrayList<>();
        retVal.add(adminDatabase);
        retVal.add(laborantDatabase);
        retVal.add(medTechnicianDatabase);
        retVal.add(patientDatabase);
        return retVal;
    }

    public void loadData() {
        this.adminDatabase.loadData();
        this.laborantDatabase.loadData();
        this.patientDatabase.loadData();

        this.measurementDatabase.loadData();
        this.analysisDatabase.loadData();
        this.analysisGroupDatabase.loadData();
        this.analysisRequestNotificationDatabase.loadData();
        this.medTechnicianDatabase.loadData();
    }



}
