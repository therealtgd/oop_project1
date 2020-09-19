package manage;

public interface EntityDatabaseFactory {
    Database getAnalysisDatabase();
    Database getAnalysisGroupDatabase();
    Database getAnalysisRequestDatabase();
    Database getMeasurementDatabase();
    Database getAnalysisRequestNotificationDatabase();

    void loadData();
}
