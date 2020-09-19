package services;

import manage.DatabaseHandler;

public abstract class Services {

    private DatabaseHandler dH;

    public Services(DatabaseHandler dH){
        this.dH = dH;
    }

    public DatabaseHandler getdH() {
        return dH;
    }
}
