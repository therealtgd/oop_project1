package view.model;

import modules.entities.Measurement;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class MeasurementModel extends DataModel<Measurement> {
    public MeasurementModel(List<Measurement> data) {
        super(data);
        columnNames = new String[]{"Izmerena vrednos", "Datum"};
    }

    @Override
    public int getRowCount() {
       return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Measurement n = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return n.getValue();
            case 1:
                return n.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE);
            default:
                return null;
        }
    }

}
