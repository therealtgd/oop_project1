package manage.entities;

import manage.Database;
import manage.FileDatabase;
import modules.entities.AnalysisRequest;
import modules.entities.AnalysisRequestNotification;
import modules.entities.MyNotification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnalysisRequestNotificationFileDatabase extends FileDatabase<AnalysisRequestNotification> {

    Database<AnalysisRequest> analysisRequestDatabase;

    public AnalysisRequestNotificationFileDatabase(String filename, Database<AnalysisRequest> analysisRequestDatabase) {
        super(filename);
        this.analysisRequestDatabase = analysisRequestDatabase;
        loadData();
    }

    @Override
    public boolean loadData() {
        setData(new ArrayList<>());
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                AnalysisRequestNotification n = new AnalysisRequestNotification(Integer.parseInt(tokens[0]), tokens[1],
                        LocalDateTime.parse(tokens[2]), tokens[3], tokens[4], Boolean.parseBoolean(tokens[5]),
                        analysisRequestDatabase.getById((Integer.parseInt(tokens[6]))));
                List<AnalysisRequestNotification> data = getData();
                data.add(n);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean saveData() {
        analysisRequestDatabase.saveData();
        return super.saveData();
    }
}
