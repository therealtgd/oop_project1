package modules.manage;

import modules.utils.AppSettings;

public abstract class DatabaseFactory {

    private AppSettings appSettings;

    public DatabaseFactory(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public AppSettings getAppSettings() {
        return appSettings;
    }

    public abstract void loadData();
}
