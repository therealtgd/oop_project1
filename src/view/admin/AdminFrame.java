package view.admin;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.Admin;
import modules.utils.MyPassword;
import net.miginfocom.swing.MigLayout;
import services.utils.PasswordUtils;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdminFrame extends JFrame {

    private Admin admin;
    private ArrayList<UserDatabase> userDatabase;

    public AdminFrame(Admin a, ArrayList<UserDatabase> userDatabase) {
        this.admin = a;
        this.userDatabase = userDatabase;
        adminFrame();
    }

    public Admin getAdmin() {
        return admin;
    }

    public ArrayList<UserDatabase> getUserDatabase() {
        return userDatabase;
    }

    private void adminFrame() {
        setTitle("Meni admina");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initAdminGUI();
        setSize(500, 500);
        setVisible(true);
    }

    private void initAdminGUI() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu profileMenu = new JMenu("Profil");
        JMenuItem passwordItem = new JMenuItem("Promeni šifru");

        profileMenu.add(passwordItem);

        JMenu userMenu = new JMenu("Korisnici");

        JMenu employeeMenu = new JMenu("Zaposleni");
        JMenuItem viewEmployeesItem = new JMenuItem("Pregled zaposlenih");
        employeeMenu.add(viewEmployeesItem);

        JMenu addUserMenu = new JMenu("Registruj zaposlenog");
        JMenuItem addLaborantItem = new JMenuItem("Laborant");
        JMenuItem addMedicalTechnicianItem = new JMenuItem("Med. tehničar");

        addUserMenu.add(addLaborantItem);
        addUserMenu.add(addMedicalTechnicianItem);
        employeeMenu.add(addUserMenu);

        JMenu removeUserMenu = new JMenu("Izbriši zaposlenog");
        JMenuItem removeLaborantItem = new JMenuItem("Laborant");
        JMenuItem removeMedicalTehnicinItem = new JMenuItem("Med. tehničar");
//        JMenuItem removePatientItem = new JMenuItem("Pacjent");

        removeUserMenu.add(removeLaborantItem);
        removeUserMenu.add(removeMedicalTehnicinItem);
//        removeUserMenu.add(removePatientItem);
        employeeMenu.add(removeUserMenu);

        JMenu reportMenu = new JMenu("Analize");

        mainMenu.add(profileMenu);
        mainMenu.add(userMenu);
        mainMenu.add(employeeMenu);
        mainMenu.add(reportMenu);

        this.setJMenuBar(mainMenu);

        viewEmployeesItem.addActionListener(e -> {
            add(new EmployeeView(userDatabase), BorderLayout.CENTER);
            pack();
        });

        addLaborantItem.addActionListener(e -> new LaborantRegistrationDialog());
        addMedicalTechnicianItem.addActionListener(e -> new MedTechRegistrationDialog());

    }

    private void registerPanel() {
        JPanel p = new JPanel();
        p.setVisible(true);
    }

    private void initRegisterGUI() {

    }

    public static void main(String[] args) {
        MyPassword mP2 = PasswordUtils.generateRandomPass("pass");
        Admin a = new Admin(0, "admin", "admi", "adminic", mP2);
        DatabaseHandler dH = new DatabaseHandler();
        AdminFrame aF = new AdminFrame(a, dH.getUserDatabase().getUsers());
    }
}
