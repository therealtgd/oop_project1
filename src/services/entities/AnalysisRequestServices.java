package services.entities;

import manage.DatabaseHandler;
import modules.DTO.AnalysisRequestDTO;
import modules.entities.AnalysisRequest;
import modules.entities.MyNotification;
import modules.users.MedicalTechnician;
import services.Services;
import services.utils.Builder;

public class AnalysisRequestServices extends Services {

    public AnalysisRequestServices() {
        super(new DatabaseHandler());
    }

    public String printAnalysisRequest(AnalysisRequest aR) {
        return aR.toString();
    }

    public void requestAnalysis(AnalysisRequestDTO aRDTO) {
        AnalysisRequest aR = Builder.buildAnalysis(aRDTO);
        aR.setId(getDatabaseHandler().getEntityDatabase().getNotificationDatabase().getMaxId() + 1);
        getDatabaseHandler().getEntityDatabase().getAnalysisRequestDatabase().addData(aR);
        new NotificationServices().sendAnalysisRequestNotification(aR);
    }

}
