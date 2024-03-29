package view.model;

import manage.users.UserDatabase;
import modules.users.Laborant;

import java.util.List;

public class LaborantModel extends UserModel<Laborant> {

    public LaborantModel(List<Laborant> data) {
        super(data);
        columnNames = new String[]{"Id", "Korisničko ime", "Ime", "Prezime", "Platna baza", "Iskustvo", "Kvalifikacije", "Specijalizacije"};

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Laborant l = (Laborant) data.get(rowIndex);
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
                return l.getSalaryBase();
            case 5:
                return l.getExperience();
            case 6:
                return l.getQualification();
            case 7:
                return l.getSpecializations();
            default:
                return null;
        }
    }
}
