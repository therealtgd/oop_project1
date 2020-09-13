package manage.entities;

import manage.Database;

public interface EntityDatabase {

    Database getAnalysisDatabase();

    Database getAnalysisRequestDatabase();

    Database getMeasurementDatabase();


}
