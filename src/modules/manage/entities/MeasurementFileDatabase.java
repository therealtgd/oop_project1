package modules.manage.entities;

import modules.entities.Measurement;
import modules.manage.FileDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MeasurementFileDatabase extends FileDatabase<Measurement> {

    private AnalysisFileDatabase analysisManager;

    public MeasurementFileDatabase(String file, AnalysisFileDatabase analysisManager) {
        super(file);
        this.analysisManager = analysisManager;
    }

    @Override
    public boolean loadData() {
       try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Measurement a = new Measurement(Integer.parseInt(tokens[0]), tokens[1], Double.parseDouble(tokens[2]));
                if (tokens.length > 3) {
					for (String qId : tokens[3].split(";")) {
						int id = Integer.parseInt(qId);
						a.setAnalysis(this.analysisManager.getById(id));
					}
				}
                addData(a);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
