package view.model;

import modules.entities.AnalysisRequest;

import java.util.List;

public class PatientAnalysisRequestModel extends AnalysisRequestModel{

    public PatientAnalysisRequestModel(List<AnalysisRequest> data) {
        super(data, new String[]{"Id", "Stanje", "Analiza (id)"});
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AnalysisRequest aR = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return aR.getId();
            case 1:
                return aR.getState();
            case 2:
                return getAnalysisIds(aR);
            default:
                return null;
        }
    }
}
