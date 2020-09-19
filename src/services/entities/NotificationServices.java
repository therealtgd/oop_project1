package services.entities;

import manage.DatabaseHandler;
import modules.entities.AnalysisRequest;
import modules.entities.MyNotification;
import modules.users.MedicalTechnician;
import services.Services;


public class NotificationServices extends Services {

    public NotificationServices() {
        super(new DatabaseHandler());
    }


    public void sendAnalysisRequestNotification(AnalysisRequest aR) {
        MyNotification n = buildNotification(aR);
        for (MedicalTechnician mT: getDatabaseHandler().getUserDatabase().getMedTechnicianDatabase().getData()) {
            mT.addNotification(n);
        }
        getDatabaseHandler().getEntityDatabase().getNotificationDatabase().addData(n);
        getDatabaseHandler().getUserDatabase().getMedTechnicianDatabase().saveData();

    }

    private MyNotification buildNotification(AnalysisRequest aR) {
        String message = "Zahtjev za analizu: " + aR.getPatient().getName() + " " + aR.getPatient().getSurname()
                + ". Kućna posjeta: " + aR.isHomeVisit();
        return new MyNotification(message);
    }

}
