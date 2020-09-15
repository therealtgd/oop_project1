package view.admin;

import manage.users.UserDatabase;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import net.miginfocom.swing.MigLayout;
import view.model.LaborantModel;
import view.model.MedicalTechnicianModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MedicalTechnicianTablePanel extends JPanel{

    protected JTable table;
    protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
    private UserDatabase medTechnicianDatabase;

    public MedicalTechnicianTablePanel(UserDatabase medTechnicianDatabase) {
        this.medTechnicianDatabase = medTechnicianDatabase;
        initGUI();
    }


    public UserDatabase getMedTechnicianDatabase() {
        return medTechnicianDatabase;
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        String title = "Pregled med. tehničara";
        Border border = BorderFactory.createTitledBorder(title);
        setBorder(border);
        JTable table;
        TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<>();

        table = new JTable(new MedicalTechnicianModel(getMedTechnicianDatabase()));
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        tableSorter.setModel((AbstractTableModel) table.getModel());
        table.setRowSorter(tableSorter);
        JScrollPane sc = new JScrollPane(table);
        add(sc, "pushx, growx");

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getTableHeader().columnAtPoint(e.getPoint());
                sort(index);
            }
        });
    }

    public void refreshData() {
        MedicalTechnicianModel sm = (MedicalTechnicianModel) this.table.getModel();
        sm.fireTableDataChanged();
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
    }};

    protected void sort(int index) {
        // index of table column

        this.medTechnicianDatabase.getData().sort(new Comparator<MedicalTechnician>() {
            int retVal = 0;

            public int compare(MedicalTechnician l1, MedicalTechnician l2) {
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
                        retVal = Integer.compare(l1.getHomeVisits(), l2.getHomeVisits());
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
        refreshData();

    }

}
