package view.medicalTechnician;

import manage.DatabaseHandler;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class NotificationView extends JPanel {

    private NotificationTablePanel notificationPanel;

    public NotificationView() {
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
        return new NotificationTablePanel(new DatabaseHandler().getEntityDatabase().getAnalysisRequestNotificationDatabase());
    }

    public void refresh() {
        notificationPanel.refresh();
    }

}
