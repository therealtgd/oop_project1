package view.patient.table;

import modules.entities.Measurement;
import net.miginfocom.swing.MigLayout;
import view.model.MeasurementModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.swing.BorderFactory.createEmptyBorder;

public class MeasurementTablePanel extends JPanel {


    protected String title;
    protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<>();
    protected JTable table;
    private List<Measurement> data;

    public MeasurementTablePanel(List<Measurement> data) {
        this.data = data;
        this.table = new JTable(new MeasurementModel(data));
        initGUI();
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("", "[]");
        setLayout(layout);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        tableSorter.setModel((AbstractTableModel) table.getModel());
        table.setRowSorter(tableSorter);

        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setViewportView(table);
        scrollPane.setBorder(createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(scrollPane, "pushx, growx");

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = table.getTableHeader().columnAtPoint(e.getPoint());
                sort(index);
            }
        });
    }

    public void refresh() {
        MeasurementModel m = (MeasurementModel) this.table.getModel();
        m.fireTableDataChanged();
    }

    private Map<Integer, Integer> sortOrder = new HashMap<Integer, Integer>() {{
        put(0, 1);
        put(1, 1);
        put(2, 1);
    }};

    protected void sort(int index) {
        // index of table column

        this.data.sort(new Comparator<Measurement>() {
            int retVal = 0;

            public int compare(Measurement m1, Measurement m2) {
                switch (index) {
                    case 0:
                        retVal = Double.compare(m1.getValue(), m2.getValue());
                        break;
                    case 1:
                        retVal = String.valueOf(m1.getDate()).compareTo(String.valueOf(m2.getDate()));
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
