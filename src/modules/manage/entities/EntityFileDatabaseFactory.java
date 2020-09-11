package modules.manage.entities;

import modules.manage.DatabaseFactory;
import modules.utils.AppSettings;

public class EntityFileDatabaseFactory extends DatabaseFactory {

    private AnalysisFileDatabase analysisManager;
    private AnalysisRequestDatabase anaReqManager;
    private MeasurementFileDatabase measurementManager;

    public EntityFileDatabaseFactory(AppSettings appSettings) {
        super(appSettings);
        this.analysisManager = new AnalysisFileDatabase(getAppSettings().getAnalysisFilename());
        this.anaReqManager = new AnalysisRequestDatabase(getAppSettings().getAnalysisRequestFilename(), analysisManager);
        this.measurementManager = new MeasurementFileDatabase(getAppSettings().getMeasurementFilename(), analysisManager);
    }

    @Override
    public void loadData() {
        this.analysisManager.loadData();
        this.anaReqManager.loadData();
        this.measurementManager.loadData();
    }
}
