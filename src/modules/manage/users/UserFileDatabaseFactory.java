package modules.manage.users;

import modules.manage.FileDatabaseFactory;
import modules.utils.AppSettings;

public class UserFileDatabaseFactory extends FileDatabaseFactory implements UserDatabaseFactory {

    private AdminFileDatabase adminDatabase;
    private LaborantFileDatabase laborantDatabase;
    private MedicalTechnicianFileDatabase medTechnicianDatabase;
    private PatientFileDatabase patientDatabase;

    public UserFileDatabaseFactory(AppSettings appSettings) {
        super(appSettings);
        this.adminDatabase = new AdminFileDatabase(getAppSettings().getAdminFilename());
        this.laborantDatabase = new LaborantFileDatabase(getAppSettings().getLaborantFilename());
        this.medTechnicianDatabase = new MedicalTechnicianFileDatabase(getAppSettings().getMedicalTechnicianFilename());
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
    public void loadData() {
        this.adminDatabase.loadData();
        this.laborantDatabase.loadData();
        this.medTechnicianDatabase.loadData();
        this.patientDatabase.loadData();
    }
}
