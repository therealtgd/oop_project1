package modules.manage.entities;

import modules.manage.ManagerFactory;
import modules.utils.AppSettings;

public class EntityManagerFactory extends ManagerFactory {

    private AnalysisManager analysisManager;
    private AnalysisRequestManager anaReqManager;
    private MeasurementManager measurementManager;

    public EntityManagerFactory(AppSettings appSettings) {
        super(appSettings);
        this.analysisManager = new AnalysisManager(getAppSettings().getAnalysisFilename());
        this.anaReqManager = new AnalysisRequestManager(getAppSettings().getAnalysisRequestFilename(), analysisManager);
        this.measurementManager = new MeasurementManager(getAppSettings().getMeasurementFilename(), analysisManager);
    }

    @Override
    public void loadData() {
        this.analysisManager.loadData();
        this.anaReqManager.loadData();
        this.measurementManager.loadData();
    }
}
