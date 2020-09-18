package manage.entities;

import modules.entities.Analysis;
import manage.FileDatabase;
import modules.users.Patient;
import modules.utils.Range;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AnalysisFileDatabase extends FileDatabase<Analysis> {

    public AnalysisFileDatabase(String file) {
        super(file);
        loadData();
    }

    @Override
    public boolean loadData() {
       try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Analysis a = new Analysis(Integer.parseInt(tokens[0]), tokens[1],
                        Range.parseRange(tokens[2]), tokens[3], Double.parseDouble(tokens[4]));
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

