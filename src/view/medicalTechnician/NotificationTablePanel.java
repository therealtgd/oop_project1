package view.medicalTechnician;

import manage.Database;
import modules.entities.AnalysisRequestNotification;
import modules.entities.AnalysisRequestNotification;
import net.miginfocom.swing.MigLayout;
import services.entities.NotificationServices;
import view.model.AnalysisRequestNotificationModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class NotificationTablePanel extends JPanel {

    protected String title;
    protected JToolBar mainToolbar = new JToolBar();
    protected JButton btnHomeVisit = new JButton();
    protected JButton btnView = new JButton();
    protected JButton btnDelete = new JButton();
    protected JTextField tfSearch = new JTextField(20);
    protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<>();
    protected JTable table;
    private Database<AnalysisRequestNotification> notificationDatabase;

    public NotificationTablePanel(Database<AnalysisRequestNotification> notificationDatabase) {
        super();
        this.notificationDatabase = notificationDatabase;
        this.title = "Pregled notifikacija";
        this.table = new JTable(new AnalysisRequestNotificationModel(notificationDatabase));
        initGUI();
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        Border border = BorderFactory.createTitledBorder(title);
        setBorder(border);

        ImageIcon addIcon = new ImageIcon("img/homeVisit.png");
        btnHomeVisit.setIcon(addIcon);
        mainToolbar.add(btnHomeVisit);

        ImageIcon viewIcon = new ImageIcon("img/viewNotification.png");
        btnView.setIcon(viewIcon);
        mainToolbar.add(btnView);

        ImageIcon deleteIcon = new ImageIcon("img/delete.png");
        btnDelete.setIcon(deleteIcon);
        mainToolbar.add(btnDelete);

        mainToolbar.setFloatable(false);
        add(mainToolbar, BorderLayout.NORTH);


        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        tableSorter.setModel((AbstractTableModel) table.getModel());
        table.setRowSorter(tableSorter);
        JScrollPane sc = new JScrollPane(table);
        add(sc, "pushx, growx");

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getTableHeader().columnAtPoint(e.getPoint());
                sort(index);
            }
        });

        add(searchPanel(), BorderLayout.SOUTH);
        initActions();
    }

    protected JPanel searchPanel() {
        JPanel p = new JPanel();
        p.setBackground(Color.YELLOW);
        p.add(new JLabel("Pretraga:"));
        p.add(tfSearch);

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changedUpdate(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (tfSearch.getText().trim().length() == 0) {
                    tableSorter.setRowFilter(null);
                } else {
                    tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
                }
            }
        });

        return p;
    }

    public void refresh() {
        AnalysisRequestNotificationModel nM = (AnalysisRequestNotificationModel) this.table.getModel();
        nM.fireTableDataChanged();
    }

    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
        put(4, 1);
        put(5, 1);
        put(6, 1);
        put(7, 1);
    }};

    protected void sort(int index) {
        // index of table column

        this.notificationDatabase.getData().sort(new Comparator<AnalysisRequestNotification>() {
            int retVal = 0;

            public int compare(AnalysisRequestNotification n1, AnalysisRequestNotification n2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(n1.getId(), n2.getId());
                        break;
                    case 1:
                        retVal = n1.getTitle().compareTo(n2.getTitle());
                    case 2:
                        retVal = n1.getDateTime().compareTo(n2.getDateTime());
                        break;
                    case 3:
                        retVal = String.valueOf(n1.getState()).compareTo(String.valueOf(n2.getState()));
                        break;
                    default:
                        System.out.println("Prosirena tabela");
                        System.exit(1);
                        break;
                }
                return retVal * sortOrder.get(index);
            }
        });

        System.out.println("column " + index + " row " + sortOrder.get(index));
        sortOrder.put(index, sortOrder.get(index) * -1);
        refresh();

    }

    protected void initActions() {
        btnHomeVisit.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);

            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                AnalysisRequestNotification n = notificationDatabase.getById(id);
                if (n.getState() != n.getStates()[0]) {
                    JOptionPane.showMessageDialog(null, "Kućna posjeta je već preuzeta.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    int selection = JOptionPane.showConfirmDialog(null, "Želim da preuzmem kućnu posjetu: ", "Preuzimanje kućne posjete", JOptionPane.YES_NO_OPTION);
                    if (selection == JOptionPane.YES_OPTION) {
                        new NotificationServices().setNotificationState(n);
                        refresh();
                    }
                }
            }
        });
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                AnalysisRequestNotification n = notificationDatabase.getById(id);
                if (n != null) {
                    if (n.getState() == n.getStates()[0]) {
                    JOptionPane.showMessageDialog(null, "Aktivna notifikacija ne može biti obrisana", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                        int selection = JOptionPane.showConfirmDialog(null,
                                "Da li ste sigurni da želite da obrišete notifikaciju?",
                                "Notifikacija " + n.getId() + " - Potvrda brisanja",
                                JOptionPane.YES_NO_OPTION);
                        if (selection == JOptionPane.YES_OPTION) {
                            notificationDatabase.remove(n.getId());
                            refresh();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Notifikacija nije pronađena.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnView.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                AnalysisRequestNotification n = notificationDatabase.getById(id);
                if (n != null) {
                    new CheckNotificationDialog(notificationDatabase, n);
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(null, "Notifikacija nije pronađena.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}
