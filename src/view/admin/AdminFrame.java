package view.admin;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.Admin;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.ProfileMenu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminFrame extends JFrame {

    private Admin admin;
    private ArrayList<UserDatabase> userDatabase;
    private EmployeeView employeeView;

    public AdminFrame(Admin a, ArrayList<UserDatabase> userDatabase) {
        this.admin = a;
        this.userDatabase = userDatabase;
        this.employeeView = null;
        adminFrame();
    }

    public Admin getAdmin() {
        return admin;
    }

    public ArrayList<UserDatabase> getUserDatabase() {
        return userDatabase;
    }

    public EmployeeView getEmployeeView() {
        return employeeView;
    }

    public void setEmployeeView(EmployeeView employeeView) {
        this.employeeView = employeeView;
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

        JMenu profileMenu = new ProfileMenu(admin);

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
            if (employeeView == null) {
                setEmployeeView(new EmployeeView(userDatabase));
                add(getEmployeeView(), BorderLayout.CENTER);
                pack();
            } else {
                getEmployeeView().refresh();
            }
        });

        addLaborantItem.addActionListener(e -> new LaborantRegistrationDialog());
        addMedicalTechnicianItem.addActionListener(e -> new MedTechRegistrationDialog());

    }


    public static void main(String[] args) {
        MyPassword mP2 = PasswordUtils.generateRandomPass("pass");
        Admin a = new Admin(0, "admin", "admi", "adminic", mP2);
        DatabaseHandler dH = new DatabaseHandler();
        AdminFrame aF = new AdminFrame(a, dH.getUserDatabase().getUsers());
    }
}
