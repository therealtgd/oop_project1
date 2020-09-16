package manage.users;

import manage.FileDatabase;
import modules.users.Laborant;
import modules.users.Patient;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.validators.exceptions.LoginException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientFileDatabase extends FileDatabase<Patient> implements UserDatabase<Patient> {

    public PatientFileDatabase(String file) {
        super(file);
    }

    @Override
    public boolean loadData() {
        setData(new ArrayList<>());
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Patient p = new Patient(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3],
                        MyPassword.parseMyPassword(tokens[4]), tokens[5]);
                if (!tokens[6].equals("null")) {
                    p.setGender(tokens[6]);
                }
                if (!tokens[7].equals("null")) {
                    p.setPhone(tokens[7]);
                }
                if (!tokens[8].equals("null")) {
                    p.setAddress(tokens[8]);
                }
                List<Patient> data = getData();
                data.add(p);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public Patient validateLogin(String username, String password, List<Patient> data) throws LoginException {
        for (Patient p : data) {
            String salt = p.getPassword().getSalt();
            String key = p.getPassword().getKey();
            if (p.getUsername().equals(username)) {
                if (PasswordUtils.verifyPassword(password, key, salt)) {
                    return p;
                } else {
                    break;
                }
            }
        } throw new LoginException("Neispravni podaci. Pokušajte ponovo.");
    }
}
