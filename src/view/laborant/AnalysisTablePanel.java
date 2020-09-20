package view.laborant;

import manage.DatabaseHandler;
import modules.entities.Analysis;
import net.miginfocom.swing.MigLayout;
import view.model.AnalysisModel;

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
import java.util.List;
import java.util.Map;

public class AnalysisTablePanel extends JPanel {

    protected String title;
    protected JToolBar mainToolbar = new JToolBar();
    protected JButton btnGenerate = new JButton();
    protected JTextField tfSearch = new JTextField(20);
    protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<>();
    protected JTable table;
    private DatabaseHandler dH;
    private List<Analysis> data;


    public AnalysisTablePanel(DatabaseHandler dH, String title, List<Analysis> data) {
        super();
        this.dH = dH;
        this.data = data;
        this.title = title;
        this.table = new JTable(new AnalysisModel(data));
        initGUI();
    }

    public DatabaseHandler getdH() {
        return dH;
    }

    public List<Analysis> getData() {
        return data;
    }

    protected void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        Border border = BorderFactory.createTitledBorder(title);
        setBorder(border);

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
        AnalysisModel m = (AnalysisModel) this.table.getModel();
        m.fireTableDataChanged();
    }

    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
        put(3, 1);
        put(4, 1);
        put(5, 1);

    }};

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<Analysis>() {
            int retVal = 0;

            public int compare(Analysis a1, Analysis a2) {
                switch (index) {
                    case 0:
                        retVal = Integer.compare(a1.getId(), a2.getId());
                        break;
                    case 1:
                        retVal = a1.getAnalysisGroup().compareTo(a2.getAnalysisGroup());
                    case 2:
                        retVal = a1.getType().compareTo(a2.getType());
                        break;
                    case 3:
                        retVal = a1.getReferenceValue().compareTo(a2.getReferenceValue());
                        break;
                    case 4:
                        retVal = a1.getUnit().compareTo(a2.getUnit());
                        break;
                    case 5:
                        retVal = Double.compare(a1.getCost(), a2.getCost());
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
}
