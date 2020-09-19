package manage.entities;

import manage.Database;
import manage.DatabaseFactory;
import manage.EntityDatabaseFactory;
import manage.FileDatabaseFactory;
import manage.users.PatientFileDatabase;
import modules.entities.*;
import modules.utils.AppSettings;

public class EntityFileDatabaseFactory implements EntityDatabaseFactory {

    private Database<Analysis> analysisDatabase;
    private Database<AnalysisGroup> analysisGroupDatabase;
    private Database<Measurement> measurementDatabase;
    private Database<AnalysisRequest> analysisRequestDatabase;
    private Database<AnalysisRequestNotification> analysisRequestNotificationDatabase;

    public EntityFileDatabaseFactory(DatabaseFactory dF) {
        this.measurementDatabase = dF.getMeasurementDatabase();
        this.analysisDatabase = dF.getAnalysisDatabase();
        this.analysisGroupDatabase = dF.getAnalysisGroupDatabase();
        this.analysisRequestDatabase = dF.getAnalysisRequestDatabase();
        this.analysisRequestNotificationDatabase = dF.getAnalysisRequestNotificationDatabase();
    }

    public void loadData() {
        this.analysisDatabase.loadData();
        this.analysisRequestDatabase.loadData();
        this.measurementDatabase.loadData();
        this.analysisRequestNotificationDatabase.loadData();
    }

    public Database<Analysis> getAnalysisDatabase() {
        return analysisDatabase;
    }

    @Override
    public Database<AnalysisGroup> getAnalysisGroupDatabase() {
        return analysisGroupDatabase;
    }

    @Override
    public Database<AnalysisRequest> getAnalysisRequestDatabase() {
        return analysisRequestDatabase;
    }

    @Override
    public Database<Measurement> getMeasurementDatabase() {
        return measurementDatabase;
    }

    @Override
    public Database<AnalysisRequestNotification> getAnalysisRequestNotificationDatabase() {
        return analysisRequestNotificationDatabase;
    }
}
