package view.model;

import manage.Database;

import javax.swing.table.AbstractTableModel;

public abstract class DataModel extends AbstractTableModel {

    protected Database database;
    protected String[] columnNames;

    public DataModel(Database database) {
        this.database = database;
    }

    @Override
    public int getRowCount() {
        return database.getData().size();
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
