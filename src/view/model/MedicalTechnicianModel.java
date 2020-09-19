package view.model;

import manage.users.UserDatabase;
import modules.users.MedicalTechnician;

public class MedicalTechnicianModel extends UserModel {


    public MedicalTechnicianModel(UserDatabase userDatabase) {
        super(userDatabase);
        columnNames = new String[]{"Id", "Korisničko ime", "Ime", "Prezime", "Platna baza", "Iskustvo", "Kućne posjete"};
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MedicalTechnician l = (MedicalTechnician) database.getData().get(rowIndex);
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
                return l.getHomeVisits();
            default:
                return null;
        }
    }
}
