package manage.entities;

import modules.entities.Analysis;
import manage.FileDatabase;
import modules.utils.Range;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AnalysisFileDatabase extends FileDatabase {

    public AnalysisFileDatabase(String file) {
        super(file);
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
                addData(a);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}

