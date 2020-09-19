package view.admin.table;

import manage.Database;
import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.Patient;
import view.admin.patient.PatientEditDialog;
import view.admin.patient.PatientRegistrationDialog;
import view.model.PatientModel;

import javax.swing.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class PatientTablePanel extends UserTablePanel {

    private DatabaseHandler dH;

    public PatientTablePanel(DatabaseHandler dH) {
        super(dH.getUserDatabase().getPatientDatabase(), new JTable(new PatientModel(dH.getUserDatabase().getPatientDatabase().getData())), "Pregled pacjenata");
        this.dH = dH;
    }

    @Override
    public void refresh() {
        PatientModel pm = (PatientModel) table.getModel();
        pm.fireTableDataChanged();
    }

    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
        put(4, 1);
        put(5, 1);
        put(6, 1);
        put(7, 1);
    }};

    @Override
    protected void sort(int index) {
        // index of table column

        this.getDatabase().getData().sort(new Comparator<Patient>() {
            int retVal = 0;

            public int compare(Patient p1, Patient p2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(p1.getId(), p2.getId());
                        break;
                    case 1:
                        retVal = p1.getUsername().compareTo(p2.getUsername());
                    case 2:
                        retVal = p1.getName().compareTo(p2.getName());
                        break;
                    case 3:
                        retVal = p1.getSurname().compareTo(p2.getSurname());
                        break;
                    case 4:
                        retVal = Integer.compare(Integer.parseInt(p1.getLBO()), Integer.parseInt(p2.getLBO()));
                        break;
                    case 5:
                        retVal = String.valueOf(p1.getGender()).compareTo(String.valueOf(p2.getGender()));
                        break;
                    case 6:
                        retVal = p1.getPhone().compareTo(p2.getPhone());
                        break;
                    case 7:
                        retVal = p1.getAddress().compareTo(p2.getAddress());
                        break;
                    default:
                        System.out.println("Prosirena tabela");
                        System.exit(1);
                        break;
                }
                return retVal * sortOrder.get(index);
            }
        });
        System.out.println("column " + index + " row " + sortOrder.get(index));
        sortOrder.put(index, sortOrder.get(index) * -1);
        refresh();
    }

    @Override
    protected void initActions() {
        btnAdd.addActionListener(e -> new PatientRegistrationDialog(dH));
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                Patient l = (Patient) getDatabase().getById(id);
                if (l != null) {
                    int selection = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da želite da obrišete pacjenta?",
                            l.getName() + " " + l.getSurname() + " - Potvrda brisanja",
                            JOptionPane.YES_NO_OPTION);
                    if (selection == JOptionPane.YES_OPTION) {
                        getDatabase().remove(l.getId());
                        refresh();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Pacjent nije pronađen.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                Patient l = (Patient) getDatabase().getById(id);
                if (l != null) {
                    new PatientEditDialog(dH, l);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "Patient nije pronađen.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
