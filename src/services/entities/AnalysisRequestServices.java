package services.entities;

import modules.entities.AnalysisRequest;
import modules.manage.DatabaseHandler;
import services.Services;

public class AnalysisRequestServices extends Services {


    public AnalysisRequestServices(DatabaseHandler databaseHandler) {
        super(databaseHandler);
    }

    public String printAnalysisRequest(AnalysisRequest aR) {
        return aR.toString();
    }

}
