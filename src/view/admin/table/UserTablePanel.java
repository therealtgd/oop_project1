package view.admin.table;

import manage.users.UserDatabase;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public abstract class UserTablePanel extends JPanel {

    protected String title;
    protected JToolBar mainToolbar = new JToolBar();
    protected JButton btnAdd = new JButton();
    protected JButton btnEdit = new JButton();
    protected JButton btnDelete = new JButton();
    protected JTextField tfSearch = new JTextField(20);
    protected TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
    protected JTable table;
    private UserDatabase database;

    public UserTablePanel(UserDatabase database, JTable table, String title) {
        super();
        this.database = database;
        this.table = table;
        this.title = title;
        initGUI();
    }

    public UserDatabase getDatabase() {
        return database;
    }

    private void initGUI() {
        MigLayout layout = new MigLayout();
        setLayout(layout);

        Border border = BorderFactory.createTitledBorder(title);
        setBorder(border);

        ImageIcon addIcon = new ImageIcon("img/addUser.png");
        btnAdd.setIcon(addIcon);
        mainToolbar.add(btnAdd);

        ImageIcon editIcon = new ImageIcon("img/edit.png");
        btnEdit.setIcon(editIcon);
        mainToolbar.add(btnEdit);

        ImageIcon deleteIcon = new ImageIcon("img/removeUser.png");
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

    public abstract void refresh();

    protected abstract void sort(int index);

    protected abstract void initActions();
}
