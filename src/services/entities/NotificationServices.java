package services.entities;

import manage.DatabaseHandler;
import modules.entities.AnalysisRequest;
import modules.entities.AnalysisRequestNotification;
import modules.users.MedicalTechnician;
import services.Services;


public class NotificationServices extends Services {

    public NotificationServices() {
        super(new DatabaseHandler());
    }


    public void sendAnalysisRequestNotification(AnalysisRequest aR) {
        AnalysisRequestNotification n = buildAnalysisRequestNotification(aR);
        for (MedicalTechnician mT : getDatabaseHandler().getUserDatabase().getMedTechnicianDatabase().getData()) {
            mT.addNotification(n);
        }
        getDatabaseHandler().getEntityDatabase().getAnalysisRequestNotificationDatabase().addData(n);
        getDatabaseHandler().getUserDatabase().getMedTechnicianDatabase().saveData();

    }

    private AnalysisRequestNotification buildAnalysisRequestNotification(AnalysisRequest aR) {
        return new AnalysisRequestNotification(aR.getPatient().getName() + " " + aR.getPatient().getSurname(), aR.isHomeVisit(), aR);
    }

    public void setNotificationState(AnalysisRequestNotification n) {
        n.setState("OPENED");
        n.getAnalysisRequest().setState("COLLECTING_SAMPLE");
        getDatabaseHandler().getEntityDatabase().getAnalysisRequestNotificationDatabase().saveData();
//        getDatabaseHandler().getEntityDatabase().getAnalysisRequestDatabase().saveData();
    }

}
