package view.model;

import manage.Database;
import modules.entities.AnalysisRequestNotification;


public class AnalysisRequestNotificationModel extends DataModel {

    public AnalysisRequestNotificationModel(Database<AnalysisRequestNotification> database) {
        super(database);
        columnNames = new String[]{"Id", "Naslov", "Stanje", "Pacjent", "Kućna posjeta", "Analiza (id)"};

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        AnalysisRequestNotification n = (AnalysisRequestNotification) database.getData().get(rowIndex);
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
