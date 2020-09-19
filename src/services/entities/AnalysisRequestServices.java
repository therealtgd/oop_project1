package services.entities;

import manage.DatabaseHandler;
import modules.DTO.AnalysisRequestDTO;
import modules.entities.AnalysisRequest;
import services.Services;
import services.utils.Builder;

public class AnalysisRequestServices extends Services {

    public AnalysisRequestServices(DatabaseHandler dH) {
        super(dH);
    }

    public String printAnalysisRequest(AnalysisRequest aR) {
        return aR.toString();
    }

    public void requestAnalysis(AnalysisRequestDTO aRDTO) {
        AnalysisRequest aR = Builder.buildAnalysiRequest(aRDTO);
        aR.setId(getDatabaseHandler().getEntityDatabase().getAnalysisRequestNotificationDatabase().getMaxId() + 1);
        getDatabaseHandler().getEntityDatabase().getAnalysisRequestDatabase().addData(aR);
        new NotificationServices().sendAnalysisRequestNotification(aR);
    }

}
