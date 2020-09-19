package manage.entities;

import manage.Database;

public interface EntityDatabase extends Database {

    Database getAnalysisDatabase();

    Database getAnalysisRequestDatabase();

    Database getMeasurementDatabase();


}
