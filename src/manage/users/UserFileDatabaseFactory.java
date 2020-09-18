package manage.users;

import manage.FileDatabaseFactory;
import manage.entities.NotificationFileDatabase;
import modules.utils.AppSettings;

import java.util.ArrayList;

public class UserFileDatabaseFactory extends FileDatabaseFactory implements UserDatabaseFactory {

    private AdminFileDatabase adminDatabase;
    private LaborantFileDatabase laborantDatabase;
    private MedicalTechnicianFileDatabase medTechnicianDatabase;
    private PatientFileDatabase patientDatabase;

    public UserFileDatabaseFactory(AppSettings appSettings) {
        super(appSettings);
        this.adminDatabase = new AdminFileDatabase(getAppSettings().getAdminFilename());
        this.laborantDatabase = new LaborantFileDatabase(getAppSettings().getLaborantFilename());
        this.medTechnicianDatabase = new MedicalTechnicianFileDatabase(getAppSettings().getMedicalTechnicianFilename(),
                new NotificationFileDatabase(getAppSettings().getNotificationFilename()));
        this.patientDatabase = new PatientFileDatabase(getAppSettings().getPatientFilename());
    }

    public AdminFileDatabase getAdminDatabase() {
        return adminDatabase;
    }

    public LaborantFileDatabase getLaborantDatabase() {
        return laborantDatabase;
    }

    public MedicalTechnicianFileDatabase getMedTechnicianDatabase() {
        return medTechnicianDatabase;
    }

    public PatientFileDatabase getPatientDatabase() {
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
