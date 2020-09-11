package modules.manage.users;

import modules.manage.entities.AnalysisGroupManager;
import modules.users.Laborant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LaborantManager extends UserManager<Laborant>{

    private AnalysisGroupManager specManager;

    public LaborantManager(String file) {
        super(file);
    }

    @Override
    public boolean loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Laborant l = new Laborant(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3],
                        tokens[4], Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
                if (tokens.length > 8) {
					for (String qId : tokens[8].split(";")) {
						int id = Integer.parseInt(qId);
						l.addSpecialization(this.specManager.getById(id));
					}
				}
                addData(l);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
