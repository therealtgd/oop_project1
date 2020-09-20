package view.patient;

import manage.DatabaseHandler;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.users.Patient;
import net.miginfocom.swing.MigLayout;
import view.laborant.AnalysisTablePanel;
import view.laborant.AvailableAnalysisFrame;
import view.model.PatientAnalysisRequestModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class AllAnalysisRequestsPanel extends JPanel {

    private DatabaseHandler dH;
    private Patient patient;
    protected JTextField tfSearch = new JTextField(20);
    protected JButton btnView;
    protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<>();
    private List<AnalysisRequest> data;
    protected JTable table;

    public AllAnalysisRequestsPanel(DatabaseHandler dH, Patient patient) {
        super();
        this.dH = dH;
        this.patient = patient;
        this.data = getAnalysesRequests();
        this.table = new JTable(new PatientAnalysisRequestModel(data));
        initGUI();
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        Border border = BorderFactory.createTitledBorder("Pregled svih zahtjeva za analizu");
        setBorder(border);

        btnView = new JButton();
        ImageIcon viewIcon = new ImageIcon("img/viewNotification.png");
        btnView.setIcon(viewIcon);

        JToolBar mainToolbar = new JToolBar();
        mainToolbar.add(btnView);

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

    private List<AnalysisRequest> getAnalysesRequests() {
        List<AnalysisRequest> retVal = new ArrayList<>();
        for (AnalysisRequest aR: dH.getEntityDatabase().getAnalysisRequestDatabase().getData()) {
            if (aR.getPatient().getId() == patient.getId()) {
                retVal.add(aR);
            }
        } return retVal;
    }

        public void refresh() {
        PatientAnalysisRequestModel m = (PatientAnalysisRequestModel) this.table.getModel();
        m.fireTableDataChanged();
    }

    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
    }};

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<AnalysisRequest>() {
            int retVal = 0;

            public int compare(AnalysisRequest a1, AnalysisRequest a2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(a1.getId(), a2.getId());
                        break;
                    case 1:
                        retVal = String.valueOf(a1.getState()).compareTo(String.valueOf(a2.getState()));
                        break;
                    case 2:
                        retVal = a1.getAnalysisMeasurementMap().keySet().toString().compareTo(a2.getAnalysisMeasurementMap().keySet().toString());
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
        btnView.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);

            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                AnalysisRequest aR = dH.getEntityDatabase().getAnalysisRequestDatabase().getById(id);
                if (aR.getState() != aR.getStates()[3]) {
                    new AvailableAnalysisFrame(new AnalysisTablePanel(dH, "Aktivne analize", getAnalyses(aR)));
                } else {
                    new FinishedAnalysisFrame(aR.getAnalysisMeasurementMap());
                }
            }
        });
    }

    private List<Analysis> getAnalyses(AnalysisRequest aR) {
        return new ArrayList<>(aR.getAnalysisMeasurementMap().keySet());
    }

}
