package view.admin;

import manage.users.UserDatabase;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class EmployeeView extends JPanel {

    private ArrayList<UserDatabase> userDatabase;

    public EmployeeView(ArrayList<UserDatabase> userDatabase) {
        this.userDatabase = userDatabase;
        MigLayout layout = new MigLayout("fill", "[]");
        setLayout(layout);
        employeePanel();
    }

    private void employeePanel() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Laboranti", laborantPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.add("Med. tehničari", medicalTechnicianPanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        add(tabbedPane, "push, grow");

    }


    private LaborantTablePanel laborantPanel() {
        UserDatabase laborantDatabase = null;
        for (UserDatabase uDb: userDatabase) {
            uDb.loadData();
            if (uDb.getData().size() > 0) {
                if (uDb.getData().get(0) instanceof Laborant) {
                    laborantDatabase = uDb;
                    break;
                }
            }
        }
        return new LaborantTablePanel(laborantDatabase);

    }


    private MedicalTechnicianTablePanel medicalTechnicianPanel() {
        UserDatabase medTechnicianDatabase = null;
        for (UserDatabase uDb: userDatabase) {
            uDb.loadData();
            if (uDb.getData().size() > 0) {
                if (uDb.getData().get(0) instanceof MedicalTechnician) {
                    medTechnicianDatabase = uDb;
                    break;
                }
            }
        }
        return new MedicalTechnicianTablePanel(medTechnicianDatabase);
    }

}