package view.model;

import modules.IData;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class DataModel<T extends IData> extends AbstractTableModel {

    protected List<T> data;
    protected String[] columnNames;

    public DataModel(List<T> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
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
