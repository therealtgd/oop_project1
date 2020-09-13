package services.user;

import manage.DatabaseHandler;

public class MedicalTechnicianServicesInjector {
    public static MedicalTechnicianServices services() {
        return new MedicalTechnicianServices(new DatabaseHandler());
    }
}
