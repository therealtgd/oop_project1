package view.model;

import manage.users.UserDatabase;
import modules.users.Patient;

import java.util.List;

public class PatientModel extends UserModel<Patient> {


    public PatientModel(List<Patient> data) {
        super(data);
        columnNames = new String[]{"Id", "Korisničko ime", "Ime", "Prezime", "LBO", "Adresa", "Pol"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Patient l = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return l.getId();
            case 1:
                return l.getUsername();
            case 2:
                return l.getName();
            case 3:
                return l.getSurname();
            case 4:
                return l.getLBO();
            case 5:
                return l.getAddress();
            case 6:
                return l.getGender();
            default:
                return null;
        }
    }
}
