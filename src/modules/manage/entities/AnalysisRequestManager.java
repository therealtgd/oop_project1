package modules.manage.entities;

import modules.entities.AnalysisRequest;
import modules.manage.FileManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalysisRequestManager extends FileManager<AnalysisRequest> {
    private AnalysisManager analysisManager;
    private MeasurementManager measurementManager;

    public AnalysisRequestManager(String file, AnalysisManager analysisManager) {
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
						aR.addAnalysis(this.analysisManager.getById(id));
					}
				} else if (tokens.length > 4) {
                    for (String qId : tokens[4].split(";")) {
						int id = Integer.parseInt(qId);
						aR.addMeasurement(this.measurementManager.getById(id));
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
