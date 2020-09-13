package manage;

public interface EntityDatabaseFactory {
    Database getAnalysisDatabase();
    Database getAnalysisRequestDatabase();
    Database getMeasurementDatabase();
    void loadData();

}
