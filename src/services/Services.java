package services;

import modules.manage.DatabaseHandler;

public abstract class Services {

    private DatabaseHandler databaseHandler;

    public Services(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }
}
