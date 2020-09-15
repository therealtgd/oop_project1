package view;

import modules.users.MedicalTechnician;

import javax.swing.*;

public class MedicalTechnicianFrame extends JFrame {

    private MedicalTechnician medicalTechnician;

    public MedicalTechnicianFrame(MedicalTechnician mT) {
        this.medicalTechnician = mT;
        medicalTechnicianFrame();
    }

    public MedicalTechnician getMedicalTechnician() {
        return medicalTechnician;
    }

    private void medicalTechnicianFrame() {
        this.setTitle("Meni med. tehničara");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initMedicalTechnicianGUI();
        this.setVisible(true);
    }

    private void initMedicalTechnicianGUI() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu profileMenu = new JMenu("Profil");
        JMenuItem passwordItem = new JMenuItem("Promeni šifru");
        profileMenu.add(passwordItem);

        JMenu notificationMenu = new JMenu("Notifikacije");
        JMenuItem showNotificationItem = new JMenuItem("Prikaži notifikacije");
        JMenuItem deleteNotificationItem = new JMenuItem("Izbriši notifikacije");

        notificationMenu.add(showNotificationItem);
        notificationMenu.add(deleteNotificationItem);

        JMenu analysisMenu = new JMenu("Analize");
        JMenuItem requestItem = new JMenuItem("Zahtjevi");

        analysisMenu.add(requestItem);

        mainMenu.add(profileMenu);
        mainMenu.add(notificationMenu);
        mainMenu.add(analysisMenu);

        this.setJMenuBar(mainMenu);
    }
}

