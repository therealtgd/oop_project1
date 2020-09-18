package manage.entities;

import manage.Database;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import manage.FileDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MeasurementFileDatabase extends FileDatabase<Measurement> {

    private Database<Analysis> analysisDatabase;

    public MeasurementFileDatabase(String file, Database<Analysis> analysisDatabase) {
        super(file);
        this.analysisDatabase = analysisDatabase;
        loadData();
    }

    @Override
    public boolean loadData() {
       try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Measurement m = new Measurement(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]));
                if (tokens.length > 3) {
					for (String qId : tokens[3].split(";")) {
						int id = Integer.parseInt(qId);
						m.setAnalysis(this.analysisDatabase.getById(id));
					}
				}
                List<Measurement> data = getData();
                data.add(m);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
