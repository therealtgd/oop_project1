package manage;

import modules.entities.*;

public interface EntityDatabaseFactory {
    Database<Analysis> getAnalysisDatabase();
    Database<AnalysisGroup> getAnalysisGroupDatabase();
    Database<AnalysisRequest> getAnalysisRequestDatabase();
    Database<Measurement> getMeasurementDatabase();
    Database<AnalysisRequestNotification> getAnalysisRequestNotificationDatabase();

    void loadData();
}
