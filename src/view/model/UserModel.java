package view.model;

import manage.users.UserDatabase;

import javax.swing.table.AbstractTableModel;

public class UserModel extends AbstractTableModel {

    protected UserDatabase userDatabase;
    protected String[] columnNames;

    public UserModel(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public int getRowCount() {
        return userDatabase.getData().size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }
}
