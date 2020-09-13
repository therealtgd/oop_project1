package manage.entities;

import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import manage.FileDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalysisRequestDatabase extends FileDatabase {
    private AnalysisFileDatabase analysisManager;
    private MeasurementFileDatabase measurementManager;

    public AnalysisRequestDatabase(String file, AnalysisFileDatabase analysisManager) {
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
                AnalysisRequest aR = new AnalysisRequest(Integer.parseInt(tokens[0]), tokens[1], tokens[2]);
                if (tokens.length > 3) {
					for (String qId : tokens[3].split(";")) {
						int id = Integer.parseInt(qId);
						aR.addAnalysis((Analysis) this.analysisManager.getById(id));
					}
				} else if (tokens.length > 4) {
                    for (String qId : tokens[4].split(";")) {
						int id = Integer.parseInt(qId);
						aR.addMeasurement((Measurement) this.measurementManager.getById(id));
					}
                }
                addData(aR);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}