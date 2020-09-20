package view.model;

import modules.entities.Analysis;

import java.util.List;

public class PatientAnalysisModel extends DataModel<Analysis> {
    public PatientAnalysisModel(List<Analysis> data) {
        super(data);
        columnNames = new String[]{"Tip", "Jedinica mere", "Referentna vrednost"};
    }

    @Override
    public int getRowCount() {
       return data.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Analysis n = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return n.getType();
            case 1:
                return n.getUnit();
            case 2:
                return n.getReferenceValue().getMin() + "-" + n.getReferenceValue().getMax();
            default:
                return null;
        }
    }

}
