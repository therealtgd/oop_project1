package view.medicalTechnician;

import manage.Database;
import manage.DatabaseHandler;
import modules.entities.AnalysisRequest;
import modules.entities.AnalysisRequestNotification;
import net.miginfocom.swing.MigLayout;
import services.entities.NotificationServices;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.Map;

public class CheckNotificationDialog extends JDialog {

    private DatabaseHandler dH;
    private Database<AnalysisRequestNotification> notificationDatabase;
    private AnalysisRequestNotification notification;

    public CheckNotificationDialog(DatabaseHandler dH, AnalysisRequestNotification notification) {
        this.dH = dH;
        this.notificationDatabase = dH.getEntityDatabase().getAnalysisRequestNotificationDatabase();
        this.notification = notification;
        notificationDialog();
    }

    private void notificationDialog() {
        setTitle("Pregled notifikacije - id: " + notification.getId());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initGUI();
        setSize(500, 500);
        setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[sg 1][sg 2]");
        setLayout(layout);

        JPanel p = new JPanel();
        Border border = new TitledBorder(notification.getTitle());
        p.setBorder(border);
        p.setLayout(layout);

        JButton btnConfirm = new JButton("Preuzmi kućnu posjetu");
        JButton btnCancel = new JButton("Izađi");

        initNotificationGUI();

        add(btnConfirm);
        add(btnCancel);

        btnConfirm.addActionListener(e -> {
            if (notification.getState() != notification.getStates()[0]) {
                JOptionPane.showMessageDialog(null, "Kućna posjeta je već preuzeta.", "Greška", JOptionPane.ERROR_MESSAGE);
            } else {
                int selection = JOptionPane.showConfirmDialog(null, "Želim da preuzmem kućnu posjetu: ", "Preuzimanje kućne posjete", JOptionPane.YES_NO_OPTION);
                if (selection == JOptionPane.YES_OPTION) {
                    new NotificationServices(dH).setNotificationOpened(notification);
                    setVisible(false);
                    dispose();
                }
            }
        });

        btnCancel.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
    }

    private void initNotificationGUI() {
        for (Map.Entry<String, Object> entry : notification.getParameters().entrySet()) {
            if (entry.getValue() instanceof AnalysisRequest) {
                initAnalysisRequestGUI(entry);
            } else if (entry.getValue() instanceof Boolean) {
                initAddressGUI(entry);
            } else {
                add(new JLabel(entry.getKey()));
                add(new JLabel(entry.getValue().toString()));
            }
        }
    }

    private void initAddressGUI(Map.Entry<String, Object> entry) {
        add(new JLabel(entry.getKey()));
        add(new JLabel(entry.getValue().toString()));
        add(new JLabel("Adresa:"));
        add(new JLabel(notification.getAnalysisRequest().getPatient().getAddress()));
    }

    private void initAnalysisRequestGUI(Map.Entry<String, Object> entry) {
        add(new JLabel(entry.getKey()));
        JScrollPane scrollPane = new JScrollPane(new AnalysisRequestPanel((AnalysisRequest) entry.getValue()));
        scrollPane.setBorder(new TitledBorder("Analize"));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, "pushx, growx");
    }
}
