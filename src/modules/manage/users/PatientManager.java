package modules.manage.users;

import modules.manage.FileManager;
import modules.users.Patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PatientManager extends FileManager<Patient> {

    public PatientManager(String file) {
        super(file);
    }

    @Override
    public boolean loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Patient p = new Patient(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3],
                        tokens[4], tokens[5], tokens[6], tokens[7], tokens[8]);
                addData(p);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
