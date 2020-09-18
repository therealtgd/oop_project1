package view.admin;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.Admin;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.ProfileMenu;
import view.admin.laborant.LaborantRegistrationDialog;
import view.admin.medicalTechnician.MedTechRegistrationDialog;
import view.admin.patient.PatientRegistrationDialog;
import view.admin.patient.PatientView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AdminFrame extends JFrame {

    private Admin admin;
    private ArrayList<UserDatabase> userDatabase;
    private EmployeeView employeeView;
    private PatientView patientView;

    public AdminFrame(Admin a, ArrayList<UserDatabase> userDatabase) {
        this.admin = a;
        this.userDatabase = userDatabase;
        this.employeeView = null;
        this.patientView = null;
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

        JMenu profileMenu = new ProfileMenu(admin);

        JMenu patientMenu = new JMenu("Pacjenti");
        JMenuItem viewPatientsItem = new JMenuItem("Pregled pacjenata");
        JMenuItem addPatientItem = new JMenuItem("Registruj pacjenta");
        JMenuItem removePatientItem = new JMenuItem("Ukloni pacjenta");

        patientMenu.add(viewPatientsItem);
        patientMenu.add(addPatientItem);
        patientMenu.add(removePatientItem);

        JMenu employeeMenu = new JMenu("Zaposleni");
        JMenuItem viewEmployeesItem = new JMenuItem("Pregled zaposlenih");
        employeeMenu.add(viewEmployeesItem);

        JMenu addEmployeeMenu = new JMenu("Registruj zaposlenog");
        JMenuItem addLaborantItem = new JMenuItem("Laborant");
        JMenuItem addMedicalTechnicianItem = new JMenuItem("Med. tehničar");

        addEmployeeMenu.add(addLaborantItem);
        addEmployeeMenu.add(addMedicalTechnicianItem);
        employeeMenu.add(addEmployeeMenu);

        JMenu removeUserMenu = new JMenu("Ukloni zaposlenog");
        JMenuItem removeLaborantItem = new JMenuItem("Laborant");
        JMenuItem removeMedicalTehnicinItem = new JMenuItem("Med. tehničar");

        removeUserMenu.add(removeLaborantItem);
        removeUserMenu.add(removeMedicalTehnicinItem);
        employeeMenu.add(removeUserMenu);

        JMenu reportMenu = new JMenu("Analize");

        mainMenu.add(profileMenu);
        mainMenu.add(patientMenu);
        mainMenu.add(employeeMenu);
        mainMenu.add(reportMenu);

        this.setJMenuBar(mainMenu);

        viewEmployeesItem.addActionListener(e -> {
            if (employeeView == null) {
                employeeView = new EmployeeView(userDatabase);
                add(employeeView, BorderLayout.CENTER);
                pack();
            } else {
                employeeView.refresh();
            }
        });


        addLaborantItem.addActionListener(e -> new LaborantRegistrationDialog());
        addMedicalTechnicianItem.addActionListener(e -> new MedTechRegistrationDialog());

        addPatientItem.addActionListener(e -> new PatientRegistrationDialog());
        viewPatientsItem.addActionListener(e -> {
            if (patientView == null) {
                patientView = new PatientView(userDatabase);
                add(patientView, BorderLayout.CENTER);
                pack();
            } else {
                patientView.refresh();
            }
        });
    }


    public static void main(String[] args) {
        MyPassword mP2 = PasswordUtils.generateRandomPass("pass");
        Admin a = new Admin(0, "admin", "admi", "adminic", mP2);
        DatabaseHandler dH = new DatabaseHandler();
        AdminFrame aF = new AdminFrame(a, dH.getUserDatabase().getUsers());
    }
}
