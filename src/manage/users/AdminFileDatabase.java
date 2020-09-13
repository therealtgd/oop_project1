package manage.users;

import manage.FileDatabase;
import modules.users.Admin;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.validators.exceptions.LoginException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AdminFileDatabase extends FileDatabase<Admin> implements UserDatabase<Admin> {

    public AdminFileDatabase(String file) {
        super(file);
    }

    @Override
    public boolean loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Admin a = new Admin(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
                        tokens[3], MyPassword.parseMyPassword(tokens[4]));
                addData(a);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }


    @Override
    public Admin validateLogin(String username, String password, List<Admin> data) throws LoginException {
        for (Admin a: data) {
            String salt = a.getPassword().getSalt();
            String key = a.getPassword().getKey();
            if (username.equals(a.getUsername()) && PasswordUtils.verifyPassword(password, key, salt)) {
                return a;
            }
        } throw new LoginException("Neispravni podaci. Pokušajte ponovo.");
    }
}
