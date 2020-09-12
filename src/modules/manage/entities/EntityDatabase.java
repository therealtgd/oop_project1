package modules.manage.entities;

import modules.manage.Database;

public interface EntityDatabase {

    Database getAnalysisDatabase();

    Database getAnalysisRequestDatabase();

    Database getMeasurementDatabase();


}
