package view.medicalTechnician;

import manage.DatabaseHandler;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.ProfileMenu;
import view.admin.patient.PatientView;
import view.laborant.LaborantFrame;

import javax.swing.*;
import java.awt.*;

public class MedicalTechnicianFrame extends JFrame {

    private MedicalTechnician medicalTechnician;
    private DatabaseHandler dH;
    private NotificationView notificationView;

    public MedicalTechnicianFrame(MedicalTechnician mT, DatabaseHandler dH) {
        this.medicalTechnician = mT;
        this.dH = dH;
        medicalTechnicianFrame();
    }

    public MedicalTechnician getMedicalTechnician() {
        return medicalTechnician;
    }

    private void medicalTechnicianFrame() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon2.png"));
        this.setTitle("Meni med. tehničara");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initMedicalTechnicianGUI();
        this.setVisible(true);
    }

    private void initMedicalTechnicianGUI() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu profileMenu = new ProfileMenu(medicalTechnician);

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

        showNotificationItem.addActionListener(e -> {
            if (notificationView == null) {
                notificationView = new NotificationView(dH, medicalTechnician);
                add(notificationView, BorderLayout.CENTER);
                pack();
            } else {
                notificationView.refresh();
            }
        });
    }

    public static void main(String[] args) {
        MyPassword mP2 = PasswordUtils.generateRandomPass("pass");
        MedicalTechnician mT = new MedicalTechnician(0, "laborant", "labo", "labaratovic", mP2, 100.10, 12);
        System.out.println(mT.toFileString());
        DatabaseHandler dH = new DatabaseHandler();
        MedicalTechnicianFrame mTF = new MedicalTechnicianFrame(mT, dH);
    }

}

