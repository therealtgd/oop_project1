package view.model;

import modules.entities.Analysis;

import java.util.List;

public class AnalysisModel extends DataModel<Analysis> {
    public AnalysisModel(List<Analysis> data) {
        super(data);
        columnNames = new String[]{"Id",  "Grupa analiza", "Tip", "Referentna vrednost", "Jedinica mere", "Cena"};

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Analysis n = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return n.getId();
            case 1:
                return n.getAnalysisGroup();
            case 2:
                return n.getType();
            case 3:
                return "(" + n.getReferenceValue().getMin() + "-" + n.getReferenceValue().getMin() + ")";
            case 4:
                return n.getUnit();
            case 5:
                return n.getCost();
            default:
                return null;
        }
    }
}
