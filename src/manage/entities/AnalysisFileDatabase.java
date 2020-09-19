package manage.entities;

import manage.Database;
import manage.DatabaseHandler;
import modules.entities.Analysis;
import manage.FileDatabase;
import modules.entities.Measurement;
import modules.users.Patient;
import modules.utils.Range;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AnalysisFileDatabase extends FileDatabase<Analysis> {

    Database<Measurement> measurementDatabase;

    public AnalysisFileDatabase(String file, Database<Measurement> measurementDatabase) {
        super(file);
        this.measurementDatabase = measurementDatabase;
        loadData();
    }

    @Override
    public boolean loadData() {
       try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Analysis a = new Analysis(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
                        Range.parseRange(tokens[3]), tokens[4], Double.parseDouble(tokens[5]));
                if (tokens.length > 6) {
                    Measurement m = measurementDatabase.getById(Integer.parseInt(tokens[6]));
                    a.addMeasurement(m);
                }
                List<Analysis> data = getData();
                data.add(a);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}

