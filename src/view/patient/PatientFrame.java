package view.patient;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.users.Patient;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.ProfileMenu;
import view.validators.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientFrame extends JFrame {

    private Patient patient;
    private List<UserDatabase> userDatabase;

    public PatientFrame(Patient p,  ArrayList<UserDatabase> userDatabase) {
        this.patient = p;
         this.userDatabase = userDatabase;
        patientFrame();
    }

    public Patient getPatient() {
        return patient;
    }

    private void patientFrame() {
        this.setTitle("Meni pacjenta");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initPatientGUI();
        this.setVisible(true);
    }

    private void initPatientGUI() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu profileMenu = new ProfileMenu(patient);
        JMenuItem phoneItem = new JMenuItem("Promeni br. telefona");
        JMenuItem addressItem = new JMenuItem("Promeni adresu");

        profileMenu.add(phoneItem);
        profileMenu.add(addressItem);

        JMenu analysisMenu = new JMenu("Analize");
        JMenuItem requestItem = new JMenuItem("Naruči analizu");
        JMenuItem anamnesisItem = new JMenuItem("Anamneza");
        JMenuItem activeAnalysisItem = new JMenuItem("Aktivna analiza");

        analysisMenu.add(requestItem);
        analysisMenu.add(anamnesisItem);
        analysisMenu.add(activeAnalysisItem);

        mainMenu.add(profileMenu);
        mainMenu.add(analysisMenu);

        this.setJMenuBar(mainMenu);

        phoneItem.addActionListener(e -> {
            String phone = JOptionPane.showInputDialog("Unesite novi br. telefona:");
            Map<String, String> errCodes = Validator.validatePhone(new HashMap<>(), phone);
            if (errCodes.isEmpty())
                    patient.setPhone(phone);
                else
                    processErrors(errCodes);
        });

        addressItem.addActionListener(e -> {
            String address = JOptionPane.showInputDialog("Unesite novu adresu:");
            Map<String, String> errCodes = Validator.validateAddress(new HashMap<>(), address);
            if (errCodes.isEmpty())
                patient.setAddress(address);
            else
                processErrors(errCodes);
        });

        requestItem.addActionListener(e -> new AnalysisRequestFrame(patient));

    }

    private void processErrors(Map<String, String> errCodes) {
        if (errCodes.containsKey("phone")) {
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("phone")), "Greška", JOptionPane.ERROR_MESSAGE);
        }
        if (errCodes.containsKey("address")) {
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("address")), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }


    public static void main(String[] args) {
        MyPassword mP2 = PasswordUtils.generateRandomPass("pass");
        Patient p = new Patient(6, "pcjent", "pacjentko", "Pacjentkic", mP2, "11114523");
        DatabaseHandler dH = new DatabaseHandler();
        PatientFrame aF = new PatientFrame(p, dH.getUserDatabase().getUsers());
    }
}
