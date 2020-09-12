package modules.manage;

import modules.utils.AppSettings;

public abstract class FileDatabaseFactory {

    AppSettings appSettings;

    public FileDatabaseFactory(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

    public AppSettings getAppSettings() {
        return appSettings;
    }

}
