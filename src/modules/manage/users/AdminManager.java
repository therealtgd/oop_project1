package modules.manage.users;

import modules.manage.FileManager;
import modules.users.Admin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdminManager extends FileManager<Admin> {

    public AdminManager(String file) {
        super(file);
    }

    @Override
    public boolean loadData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Admin a = new Admin(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3],
                        tokens[4]);
                addData(a);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
