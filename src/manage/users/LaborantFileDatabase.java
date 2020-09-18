package manage.users;

import manage.FileDatabase;
import manage.entities.SpecializationFileDatabase;
import modules.users.Laborant;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.validators.exceptions.LoginException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LaborantFileDatabase extends FileDatabase<Laborant> implements UserDatabase<Laborant>{

    private SpecializationFileDatabase specManager;

    public LaborantFileDatabase(String file) {
        super(file);
        loadData();
    }


    @Override
    public boolean loadData() {
        setData(new ArrayList<>());
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
                List<Laborant> data = getData();
                data.add(l);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void edit(Laborant editLaborant) {
        for (Laborant l : getData()) {
            if (l.getId() == editLaborant.getId()) {
                editLaborant.setPassword(l.getPassword());
                remove(l.getId());
                addData(editLaborant);
            }
        }
    }


    @Override
    public Laborant validateLogin(String username, String password, List<Laborant> data) throws LoginException {
        for (Laborant u: data) {
            String salt = u.getPassword().getSalt();
            String key = u.getPassword().getKey();
            if (username.equals(u.getUsername()) && PasswordUtils.verifyPassword(password, key, salt)) {
                return u;
            }
        } throw new LoginException("Neispravni podaci. Pokušajte ponovo.");
    }
}

