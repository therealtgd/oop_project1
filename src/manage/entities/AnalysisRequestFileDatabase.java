package manage.entities;

import manage.Database;
import manage.FileDatabase;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import modules.users.Patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AnalysisRequestFileDatabase extends FileDatabase<AnalysisRequest> {

    private Database<Analysis> analysisDatabase;
    private Database<Measurement> measurementDatabase;
    private Database<Patient> patientDatabase;

    public AnalysisRequestFileDatabase(String file, Database<Analysis> analysisManager, Database<Measurement> measurementDatabase, Database<Patient> patientDatabase) {
        super(file);
        this.analysisDatabase = analysisManager;
        this.measurementDatabase = measurementDatabase;
        this.patientDatabase = patientDatabase;
        loadData();
    }

    @Override
    public boolean loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                AnalysisRequest aR = new AnalysisRequest(Integer.parseInt(tokens[0]), patientDatabase.getById(Integer.parseInt(tokens[1])), tokens[2]);
                if (tokens.length > 3) {
                    for (String qId : tokens[3].split(";")) {
                        int id = Integer.parseInt(qId);
                        aR.addAnalysis(analysisDatabase.getById(id));
                    }
                }
                if (tokens.length > 4) {
                    for (String qId : tokens[4].split(";")) {
                        int id = Integer.parseInt(qId);
                        aR.addMeasurement(measurementDatabase.getById(id));
                    }
                }
                List<AnalysisRequest> data = getData();
                data.add(aR);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
