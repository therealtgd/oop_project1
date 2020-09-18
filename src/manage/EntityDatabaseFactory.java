package manage;

public interface EntityDatabaseFactory {
    Database getAnalysisDatabase();
    Database getAnalysisGroupDatabase();
    Database getAnalysisRequestDatabase();
    Database getMeasurementDatabase();
    Database getNotificationDatabase();

    void loadData();
}
