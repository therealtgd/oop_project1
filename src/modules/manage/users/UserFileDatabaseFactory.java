package modules.manage.users;

import modules.manage.DatabaseFactory;
import modules.utils.AppSettings;

public class UserFileDatabaseFactory extends DatabaseFactory {

    private AdminFileDatabase adminManager;
    private LaborantFileDatabase laborantManager;
    private MedicalTechnicianFileDatabase medTechnicianManager;
    private PatientFileDatabase patientManager;

    public UserFileDatabaseFactory(AppSettings appSettings) {
        super(appSettings);
        this.adminManager = new AdminFileDatabase(getAppSettings().getAdminFilename());
        this.laborantManager = new LaborantFileDatabase(getAppSettings().getLaborantFilename());
        this.medTechnicianManager = new MedicalTechnicianFileDatabase(getAppSettings().getMedicalTechnicianFilename());
        this.patientManager = new PatientFileDatabase(getAppSettings().getPatientFilename());
    }

    public AdminFileDatabase getAdminManager() {
        return adminManager;
    }

    public LaborantFileDatabase getLaborantManager() {
        return laborantManager;
    }

    public MedicalTechnicianFileDatabase getMedTechnicianManager() {
        return medTechnicianManager;
    }

    public PatientFileDatabase getPatientManager() {
        return patientManager;
    }

    @Override
    public void loadData() {
        this.adminManager.loadData();
        this.laborantManager.loadData();
        this.medTechnicianManager.loadData();
        this.patientManager.loadData();
    }
}
