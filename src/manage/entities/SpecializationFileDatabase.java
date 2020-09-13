package manage.entities;

import modules.entities.Analysis;
import modules.entities.AnalysisGroup;
import manage.FileDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SpecializationFileDatabase extends FileDatabase {

    @Override
    public boolean loadData() {

        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                AnalysisGroup aG = new AnalysisGroup(Integer.parseInt(tokens[0]), tokens[1]);
                if (tokens.length > 2) {
                    ArrayList<Analysis> analyses;
                    String[] tA = tokens[2].split(";");

                }
                addData(aG);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

}
