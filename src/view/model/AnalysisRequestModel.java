package view.model;

import modules.entities.Analysis;
import modules.entities.AnalysisRequest;

import java.util.ArrayList;
import java.util.List;

public class AnalysisRequestModel extends DataModel<AnalysisRequest>{

    public AnalysisRequestModel(List<AnalysisRequest> data) {
        super(data);
        columnNames = new String[]{"Id", "Pacjent", "Stanje", "Analiza (id)"};

    }

    public AnalysisRequestModel(List<AnalysisRequest> data, String[] columnNames) {
        super(data);
        super.columnNames = columnNames;

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AnalysisRequest aR = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return aR.getId();
            case 1:
                return getPatientName(aR);
            case 2:
                return aR.getState();
            case 3:
                return getAnalysisIds(aR);
            default:
                return null;
        }
    }

    List<Integer> getAnalysisIds(AnalysisRequest aR) {
        List<Integer> retVal = new ArrayList<>();
        for (Analysis a: aR.getAnalysisMeasurementMap().keySet()) {
            retVal.add(a.getId());
        }
        return retVal;
    }

    private String getPatientName(AnalysisRequest aR) {
        return "korisnicko ime: " + aR.getPatient().getUsername() + ", ime: " + aR.getPatient().getName() + " " + aR.getPatient().getSurname();
    }

}
