package modules.manage.users;

import modules.manage.FileDatabase;
import modules.users.MedicalTechnician;
import modules.users.User;
import modules.utils.MyPassword;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MedicalTechnicianFileDatabase extends FileDatabase implements UserDatabase{
    public MedicalTechnicianFileDatabase(String file) {
        super(file);
    }

    @Override
    public boolean loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                MedicalTechnician mT = new MedicalTechnician(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
                        tokens[3],  MyPassword.parseMyPassword(tokens[4]), Double.parseDouble(tokens[5]),
                        Integer.parseInt(tokens[6]));
                addData(mT);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    @Override
    public User validateLogin(String username, String password) {
        return null;
    }
}

