package view.admin.table;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.Laborant;
import view.admin.laborant.LaborantEditDialog;
import view.admin.laborant.LaborantRegistrationDialog;
import view.model.LaborantModel;

import javax.swing.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LaborantTablePanel extends UserTablePanel {

    private DatabaseHandler dH;

    public LaborantTablePanel(DatabaseHandler dH) {
        super(dH.getUserDatabase().getLaborantDatabase(), new JTable(new LaborantModel(dH.getUserDatabase().getLaborantDatabase().getData())), "Pregled laboranata");
        this.dH = dH;
    }

    @Override
    public void refresh() {
        LaborantModel lm = (LaborantModel) this.table.getModel();
        lm.fireTableDataChanged();
    }

    // Pamcenje redosleda sortiranja za svaku kolonu posebno - primer
    @SuppressWarnings("serial")
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

        this.getDatabase().getData().sort(new Comparator<Laborant>() {
            int retVal = 0;

            public int compare(Laborant l1, Laborant l2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(l1.getId(), l2.getId());
                        break;
                    case 1:
                        retVal = l1.getUsername().compareTo(l2.getUsername());
                    case 2:
                        retVal = l1.getName().compareTo(l2.getName());
                        break;
                    case 3:
                        retVal = l1.getSurname().compareTo(l2.getSurname());
                        break;
                    case 4:
                        retVal = Double.compare(l1.getSalaryBase(), l2.getSalaryBase());
                        break;
                    case 5:
                        retVal = Integer.compare(l1.getExperience(), l2.getExperience());
                        break;
                    case 6:
                        retVal = l1.getQualification().compareTo(l2.getQualification());
                        break;
                    case 7:
                        retVal = Integer.compare(l1.getSpecializations().size(), l2.getSpecializations().size());
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
        btnAdd.addActionListener(e -> new LaborantRegistrationDialog(dH));
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                Laborant l = (Laborant) getDatabase().getById(id);
                if (l != null) {
                    int selection = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da želite da obrišete laboranta?",
                            l.getName() + " " + l.getSurname() + " - Potvrda brisanja",
                            JOptionPane.YES_NO_OPTION);
                    if (selection == JOptionPane.YES_OPTION) {
                        getDatabase().remove(l.getId());
                        refresh();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Laborant nije pronađen.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEdit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                Laborant l = (Laborant) getDatabase().getById(id);
                if (l != null) {
                    new LaborantEditDialog(dH, l);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "Laborant nije pronađen.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

}


