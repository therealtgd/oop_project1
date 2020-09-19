package manage.entities;

import manage.Database;
import manage.FileDatabase;
import modules.Data;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import modules.users.Patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisRequestFileDatabase extends FileDatabase<AnalysisRequest> {

    private Database<Analysis> analysisDatabase;
    private Database<Patient> patientDatabase;
    private Database<Measurement> measurementDatabase;

    public AnalysisRequestFileDatabase(String file, Database<Analysis> analysisDatabase, Database<Patient> patientDatabase, Database<Measurement> measurementDatabase) {
        super(file);
        this.analysisDatabase = analysisDatabase;
        this.patientDatabase = patientDatabase;
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
                AnalysisRequest aR = new AnalysisRequest(Integer.parseInt(tokens[0]), patientDatabase.getById(Integer.parseInt(tokens[1])), tokens[2]);
                List<Analysis> aList = new ArrayList<>();
                if (tokens.length > 3) {
                    for (String qId : tokens[3].split(";")) {
                        int id = Integer.parseInt(qId);
                        aList.add(analysisDatabase.getById(id));
                    }
                }
                List<Measurement> mList = new ArrayList<>();
                if (tokens.length > 4) {
                    for (String qId : tokens[4].split(";")) {
                        int id = Integer.parseInt(qId);
                        mList.add(measurementDatabase.getById(id));
                    }
                }
                Map<Analysis, Measurement> map = new HashMap<>();
                for (int i=0; i <aList.size();i++) {
                    map.put(aList.get(i), mList.get(i));
                }
                aR.setAnalysisMeasurementMap(map);
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
