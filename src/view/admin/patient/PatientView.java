package view.admin.patient;

import manage.Database;
import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.Patient;
import net.miginfocom.swing.MigLayout;
import view.admin.table.PatientTablePanel;
import view.admin.table.UserTablePanel;

import javax.swing.*;
import java.util.ArrayList;

public class PatientView extends JPanel {

    private DatabaseHandler dH;
    private ArrayList<UserDatabase> userDatabase;
    private UserTablePanel patientPanel;

    public PatientView(DatabaseHandler dH) {
        this.dH = dH;
        this.userDatabase = dH.getUserDatabase().getUsers();
        this.patientPanel = patientPanel();
        MigLayout layout = new MigLayout("fill", "[]");
        setLayout(layout);
        initPanel();
    }

    private void initPanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Pacjenti", patientPanel);

        add(tabbedPane, "push, grow");

    }

    private PatientTablePanel patientPanel() {
        UserDatabase pDB = null;
        for (UserDatabase uDb: userDatabase) {
            uDb.loadData();
            if (uDb.getData().size() > 0) {
                if (uDb.getData().get(0) instanceof Patient) {
                    pDB = uDb;
                    break;
                }
            }
        }
        return new PatientTablePanel(dH);

    }

    public void refresh() {
        patientPanel.refresh();
    }

}
