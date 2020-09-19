package manage.entities;

import manage.FileDatabase;
import modules.entities.Measurement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MeasurementFileDatabase extends FileDatabase<Measurement> {


    public MeasurementFileDatabase(String file) {
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
                Measurement m = new Measurement(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]), Double.parseDouble(tokens[3]));
                if (!tokens[2].equals("null")) {
                    m.setDate(tokens[2]);
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
