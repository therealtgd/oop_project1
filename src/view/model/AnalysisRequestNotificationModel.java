package view.model;

import manage.Database;
import modules.entities.AnalysisRequestNotification;

import java.util.List;


public class AnalysisRequestNotificationModel extends DataModel<AnalysisRequestNotification> {

    public AnalysisRequestNotificationModel(List<AnalysisRequestNotification> data) {
        super(data);
        columnNames = new String[]{"Id", "Naslov", "Stanje", "Pacjent", "Kućna posjeta", "Analiza (id)"};

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AnalysisRequestNotification n = data.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return n.getId();
            case 1:
                return n.getTitle();
            case 2:
                return n.getState();
            case 3:
                return n.getPatient();
            case 4:
                return n.isHomeVisit();
            case 5:
                return n.getAnalysisRequest().getId();
            default:
                return null;
        }
    }
}
