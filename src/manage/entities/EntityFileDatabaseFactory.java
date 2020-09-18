package manage.entities;

import manage.Database;
import manage.EntityDatabaseFactory;
import manage.FileDatabaseFactory;
import manage.users.PatientFileDatabase;
import modules.entities.*;
import modules.utils.AppSettings;

public class EntityFileDatabaseFactory extends FileDatabaseFactory implements EntityDatabaseFactory {

    private Database<Analysis> analysisDatabase;
    private Database<AnalysisGroup> analysisGroupDatabase;
    private Database<Measurement> measurementDatabase;
    private Database<AnalysisRequest> analysisRequestDatabase;
    private Database<MyNotification> notificationDatabase;

    public EntityFileDatabaseFactory(AppSettings appSettings) {
        super(appSettings);
        this.analysisDatabase = new AnalysisFileDatabase(getAppSettings().getAnalysisFilename());
        this.analysisGroupDatabase = new AnalysisGroupFileDatabase(getAppSettings().getAnalysisGroupFilename(), analysisDatabase);
        this.measurementDatabase = new MeasurementFileDatabase(getAppSettings().getMeasurementFilename(), analysisDatabase);
        this.analysisRequestDatabase = new AnalysisRequestFileDatabase(getAppSettings().getAnalysisRequestFilename(),
                analysisDatabase, measurementDatabase, new PatientFileDatabase(getAppSettings().getPatientFilename()));
        this.notificationDatabase = new NotificationFileDatabase(getAppSettings().getNotificationFilename());
    }

    public void loadData() {
        this.analysisDatabase.loadData();
        this.analysisRequestDatabase.loadData();
        this.measurementDatabase.loadData();
        this.notificationDatabase.loadData();
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
    public Database<MyNotification> getNotificationDatabase() {
        return notificationDatabase;
    }
}
