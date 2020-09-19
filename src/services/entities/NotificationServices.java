package services.entities;

import manage.DatabaseHandler;
import modules.entities.AnalysisRequest;
import modules.entities.AnalysisRequestNotification;
import modules.users.MedicalTechnician;
import services.Services;


public class NotificationServices extends Services {

    public NotificationServices(DatabaseHandler dH) {
        super(dH);
    }


    public void sendAnalysisRequestNotification(AnalysisRequest aR) {
        AnalysisRequestNotification n = buildAnalysisRequestNotification(aR);
        for (MedicalTechnician mT : getdH().getUserDatabase().getMedTechnicianDatabase().getData()) {
            mT.addNotification(n);
        }
        getdH().getEntityDatabase().getAnalysisRequestNotificationDatabase().addData(n);
        getdH().getUserDatabase().getMedTechnicianDatabase().saveData();

    }

    private AnalysisRequestNotification buildAnalysisRequestNotification(AnalysisRequest aR) {
        return new AnalysisRequestNotification(aR.getPatient().getName() + " " + aR.getPatient().getSurname(), aR.isHomeVisit(), aR);
    }

    public void setNotificationOpened(AnalysisRequestNotification n) {
        n.setState("OPENED");
        n.getAnalysisRequest().setState("COLLECTING_SAMPLE");
        getdH().getEntityDatabase().getAnalysisRequestNotificationDatabase().saveData();
    }

    public void setNotificationDeleted(AnalysisRequestNotification n) {
        n.setState("DELETED");
        n.getAnalysisRequest().setState("PROCESSING");
        getdH().getEntityDatabase().getAnalysisRequestNotificationDatabase().saveData();
    }

}
