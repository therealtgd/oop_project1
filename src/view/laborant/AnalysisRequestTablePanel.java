package view.laborant;

import manage.DatabaseHandler;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import modules.users.Laborant;
import net.miginfocom.swing.MigLayout;
import view.model.AnalysisRequestModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.*;

public class AnalysisRequestTablePanel extends JPanel {

    protected String title;
    protected JToolBar mainToolbar = new JToolBar();
    protected JButton btnCreate = new JButton();
    protected JTextField tfSearch = new JTextField(20);
    protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<>();
    protected JTable table;
    private DatabaseHandler dH;
    private List<AnalysisRequest> data;
    private Laborant laborant;


    public AnalysisRequestTablePanel(DatabaseHandler dH, String title, List<AnalysisRequest> data, Laborant laborant) {
        super();
        this.dH = dH;
        this.data = data;
        this.title = title;
        this.laborant = laborant;
        this.table = new JTable(new AnalysisRequestModel(data));
        initGUI();
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        Border border = BorderFactory.createTitledBorder(title);
        setBorder(border);

        ImageIcon createIcon = new ImageIcon("img/create.png");
        btnCreate.setIcon(createIcon);
        mainToolbar.add(btnCreate);

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
        AnalysisRequestModel m = (AnalysisRequestModel) this.table.getModel();
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
                        retVal = a1.getPatient().getName().compareTo(a2.getPatient().getName());
                    case 2:
                        retVal = String.valueOf(a1.getState()).compareTo(String.valueOf(a2.getState()));
                        break;
                    case 3:
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
        btnCreate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);

            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                AnalysisRequest aR = dH.getEntityDatabase().getAnalysisRequestDatabase().getById(id);
                if (aR.getState() != aR.getStates()[2]) {
                    JOptionPane.showMessageDialog(null, "Zahtev još nije spreman za obradu.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<Analysis> aList = new ArrayList<>();
                    for (Analysis a : aR.getAnalysisMeasurementMap().keySet()) {
                        if (laborant.getSpecializations().contains(a.getAnalysisGroup())) {
                            for (Map.Entry<Analysis, Measurement> entry : aR.getAnalysisMeasurementMap().entrySet()) {
                                if (entry.getKey().getId() == a.getId()) {
                                    if (entry.getValue().getValue() == 0.0) {
                                        aList.add(a);
                                    }
                                }
                            }
                        }
                    }
                    new AvailableAnalysisFrame(new AvailableAnalysisTablePanel(dH, aList, aR));
                    refresh();
                }
            }
        });
    }

}
