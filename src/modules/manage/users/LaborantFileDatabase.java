package modules.manage.users;

import modules.manage.FileDatabase;
import modules.manage.entities.SpecializationFileDatabase;
import modules.users.Laborant;
import modules.utils.MyPassword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LaborantFileDatabase extends FileDatabase {

    private SpecializationFileDatabase specManager;

    public LaborantFileDatabase(String file) {
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
                        MyPassword.parseMyPassword(tokens[4]), Double.parseDouble(tokens[5]), Integer.parseInt(tokens[6]), tokens[7]);
                if (tokens.length > 8) {
					for (String s : tokens[8].split(";")) {
						l.addSpecialization(s);
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

