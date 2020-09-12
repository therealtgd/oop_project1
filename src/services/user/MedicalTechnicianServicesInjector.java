package services.user;

import modules.manage.DatabaseHandler;

public class MedicalTechnicianServicesInjector {
    public static MedicalTechnicianServices services() {
        return new MedicalTechnicianServices(new DatabaseHandler());
    }
}
