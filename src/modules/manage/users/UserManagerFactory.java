package modules.manage.users;

import modules.manage.ManagerFactory;
import modules.utils.AppSettings;

public class UserManagerFactory extends ManagerFactory {

    private AdminManager adminManager;
    private LaborantManager laborantManager;
    private MedicalTechnicianManager medTechnicianManager;
    private PatientManager patientManager;

    public UserManagerFactory(AppSettings appSettings) {
        super(appSettings);
        this.adminManager = new AdminManager(getAppSettings().getAdminFilename());
        this.laborantManager = new LaborantManager(getAppSettings().getLaborantFilename());
        this.medTechnicianManager = new MedicalTechnicianManager(getAppSettings().getMedicalTechnicianFilename());
        this.patientManager = new PatientManager(getAppSettings().getPatientFilename());
    }

    public AdminManager getAdminManager() {
        return adminManager;
    }

    public LaborantManager getLaborantManager() {
        return laborantManager;
    }

    public MedicalTechnicianManager getMedTechnicianManager() {
        return medTechnicianManager;
    }

    public PatientManager getPatientManager() {
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
