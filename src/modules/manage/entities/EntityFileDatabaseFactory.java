package modules.manage.entities;

import modules.manage.EntityDatabaseFactory;
import modules.manage.FileDatabaseFactory;
import modules.utils.AppSettings;

public class EntityFileDatabaseFactory extends FileDatabaseFactory implements EntityDatabaseFactory {

    private AnalysisFileDatabase analysisDatabase;
    private AnalysisRequestDatabase analysisRequestDatabase;
    private MeasurementFileDatabase measurementDatabase;

    public EntityFileDatabaseFactory(AppSettings appSettings) {
        super(appSettings);
        this.analysisDatabase = new AnalysisFileDatabase(getAppSettings().getAnalysisFilename());
        this.analysisRequestDatabase = new AnalysisRequestDatabase(getAppSettings().getAnalysisRequestFilename(), analysisDatabase);
        this.measurementDatabase = new MeasurementFileDatabase(getAppSettings().getMeasurementFilename(), analysisDatabase);
    }

    public void loadData() {
        this.analysisDatabase.loadData();
        this.analysisRequestDatabase.loadData();
        this.measurementDatabase.loadData();
    }

    public AnalysisFileDatabase getAnalysisDatabase() { return analysisDatabase; }

    public AnalysisRequestDatabase getAnalysisRequestDatabase() {
        return analysisRequestDatabase;
    }

    public MeasurementFileDatabase getMeasurementDatabase() {
        return measurementDatabase;
    }
}
