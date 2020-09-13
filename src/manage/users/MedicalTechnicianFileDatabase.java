package manage.users;

import manage.FileDatabase;
import modules.users.MedicalTechnician;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.validators.exceptions.LoginException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class MedicalTechnicianFileDatabase extends FileDatabase<MedicalTechnician> implements UserDatabase<MedicalTechnician> {
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
    public MedicalTechnician validateLogin(String username, String password, List<MedicalTechnician> data) throws LoginException {
        for (MedicalTechnician mT: data) {
            String salt = mT.getPassword().getSalt();
            String key = mT.getPassword().getKey();
            if (username.equals(mT.getUsername()) && PasswordUtils.verifyPassword(password, key, salt)) {
                return mT;
            }
        } throw new LoginException("Neispravni podaci. Pokušajte ponovo.");
    }
}

