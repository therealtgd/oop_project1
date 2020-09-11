package modules.manage;

import modules.utils.AppSettings;

public abstract class ManagerFactory {

    private AppSettings appSettings;

    public ManagerFactory(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public AppSettings getAppSettings() {
        return appSettings;
    }

    public abstract void loadData();
}
