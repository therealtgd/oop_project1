package manage.entities;

import manage.Database;
import manage.FileDatabase;
import modules.entities.Analysis;
import modules.entities.AnalysisGroup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AnalysisGroupFileDatabase extends FileDatabase<AnalysisGroup> {

    private Database<Analysis> analysisDatabase;

    public AnalysisGroupFileDatabase(String file, Database<Analysis> analysisDatabase) {
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
                AnalysisGroup aG = new AnalysisGroup(Integer.parseInt(tokens[0]), tokens[1]);
                if (tokens.length > 2) {
                    for (String aId : tokens[2].split(";")) {
                        int id = Integer.parseInt(aId);
                        aG.addAnalyses(this.analysisDatabase.getById(id));
                    }
                }
                List<AnalysisGroup> data = getData();
                data.add(aG);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
