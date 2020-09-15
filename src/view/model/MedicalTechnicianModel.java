package view.model;

import manage.users.UserDatabase;
import modules.users.Laborant;
import modules.users.MedicalTechnician;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class MedicalTechnicianModel extends AbstractTableModel {
   private UserDatabase userDatabase;
    private String[] columnNames = {"Id", "Korisničko ime", "Ime", "Prezime", "Platna baza", "Iskustvo", "Kućne posjete"};


    public MedicalTechnicianModel(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public int getRowCount() {
        return userDatabase.getData().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MedicalTechnician l = (MedicalTechnician) userDatabase.getData().get(rowIndex);
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

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
