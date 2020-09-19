package manage.users;

import manage.Database;
import manage.DatabaseHandler;
import manage.FileDatabase;
import manage.entities.AnalysisRequestNotificationFileDatabase;
import modules.Data;
import modules.entities.AnalysisRequestNotification;
import modules.entities.MyNotification;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.validators.exceptions.LoginException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedicalTechnicianFileDatabase extends FileDatabase<MedicalTechnician> implements UserDatabase<MedicalTechnician> {

    private Database<AnalysisRequestNotification> notificationDatabase;

    public MedicalTechnicianFileDatabase(String file, Database<AnalysisRequestNotification> notificationDatabase) {
        super(file);
        this.notificationDatabase = notificationDatabase;
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
                MedicalTechnician mT = new MedicalTechnician(Integer.parseInt(tokens[0]), tokens[1], tokens[2],
                        tokens[3],  MyPassword.parseMyPassword(tokens[4]), Double.parseDouble(tokens[5]),
                        Integer.parseInt(tokens[6]), Integer.parseInt(tokens[7]));
                if (tokens.length > 8) {
                    for (String nId: tokens[8].split(";")) {
                        int id = Integer.parseInt(nId);
                        mT.addNotification(notificationDatabase.getById(id));
                    }
                }
                List<MedicalTechnician> data = getData();
                data.add(mT);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public void edit(MedicalTechnician editMedTechnician) {
        for (MedicalTechnician mT : getData()) {
            if (mT.getId() == editMedTechnician.getId()) {
                editMedTechnician.setPassword(mT.getPassword());
                remove(mT.getId());
                addData(editMedTechnician);
            }
        }
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

