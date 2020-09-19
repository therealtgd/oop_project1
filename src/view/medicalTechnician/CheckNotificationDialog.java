package view.medicalTechnician;

import manage.Database;
import modules.entities.MyNotification;
import net.miginfocom.swing.MigLayout;
import view.utils.QualificationsComboBox;
import view.utils.SpecializationsPanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class CheckNotificationDialog extends JDialog {

    private Database<MyNotification> notificationDatabase;
    private MyNotification notification;

    public CheckNotificationDialog(Database<MyNotification>  notificationDatabase, MyNotification notification) {
        this.notificationDatabase = notificationDatabase;
        this.notification = notification;
        notificationDialog();
    }

    private void notificationDialog() {
        this.setTitle("Pregled notifikacije - id: " + notification.getId());
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("", "[][]");
        setLayout(layout);

        JPanel p = new JPanel();
        Border border = new TitledBorder(notification.getTitle());
        p.setBorder(border);
        p.setLayout(layout);

        usernameTxt = new JTextField();
        nameTxt = new JTextField();
        surnameTxt = new JTextField();
        salaryTxt = new JTextField();
        xpTxt = new JTextField();
        quaComboBox = new QualificationsComboBox();
        specPanel = new SpecializationsPanel();
        confirmBtn = new JButton("Potvrdi");
        JButton cancelBtn = new JButton("Izađi");


        add(new JLabel("Korisničko ime:"), "split 2, sg 1");
        add(usernameTxt, "pushx, growx, wrap");
        add(new JLabel("Ime:"), "split 2, sg 1");
        add(nameTxt, "pushx, growx, wrap");
        add(new JLabel("Prezime:"), "split 2, sg 1");
        add(surnameTxt, "pushx, growx, wrap");
        add(new JLabel("Platna osnova:"), "split 2, sg 1");
        add(salaryTxt, "pushx, growx, wrap");
        add(new JLabel("Kvalifikacija:"), "split 2, sg 1");
        add(quaComboBox, "pushx, growx, wrap");
        add(new JLabel("Iskustvo:"), "split 4, sg 1");
        add(xpTxt, "pushx, growx");
        add(specPanel, "wrap");
        add(confirmBtn, "split 2");
        add(cancelBtn);

        cancelBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
    }
}
