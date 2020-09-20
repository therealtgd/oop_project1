package view.medicalTechnician;

import manage.DatabaseHandler;
import modules.users.MedicalTechnician;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class NotificationView extends JPanel {

    private NotificationTablePanel notificationPanel;
    private DatabaseHandler dH;
    private MedicalTechnician mT;

    public NotificationView(DatabaseHandler dH, MedicalTechnician mT) {
        this.dH = dH;
        this.mT = mT;
        this.notificationPanel = notificationPanel();
        MigLayout layout = new MigLayout("fill", "[]");
        setLayout(layout);
        notificationView();
    }

    private void notificationView() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Notifikacije", notificationPanel);

        add(tabbedPane, "push, grow");
    }

    private NotificationTablePanel notificationPanel() {
        return new NotificationTablePanel(dH, mT);
    }

    public void refresh() {
        notificationPanel.refresh();
    }

}
