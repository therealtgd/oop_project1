package services;

import manage.DatabaseHandler;

public abstract class Services {

    private DatabaseHandler databaseHandler;

    public Services(){
        this.databaseHandler = new DatabaseHandler();
    }

    public Services(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public DatabaseHandler getDatabaseHandler() {
        return databaseHandler;
    }
}
