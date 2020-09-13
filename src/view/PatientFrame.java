package view;

import modules.users.Patient;

import javax.swing.*;

public class PatientFrame extends JFrame {

    private Patient patient;

    public PatientFrame(Patient p) {
        this.patient = p;
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

        JMenu profileMenu = new JMenu("Profil");
        JMenuItem passwordItem = new JMenuItem("Promeni šifru");
        JMenuItem phoneItem = new JMenuItem("Promeni br. telefona");
        JMenuItem addressItem = new JMenuItem("Promeni adresu");

        profileMenu.add(passwordItem);
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

    }
}
