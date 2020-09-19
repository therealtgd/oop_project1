package view.model;

import manage.Database;
import manage.entities.EntityDatabase;
import modules.entities.MyNotification;


public class NotificationModel extends DataModel {

    public NotificationModel(Database database) {
        super(database);
        columnNames = new String[]{"Id", "Poruka", "Stanje"};

    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MyNotification n = (MyNotification) database.getData().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return n.getId();
            case 1:
                return n.getMessage();
            case 2:
                return n.getState();
            default:
                return null;
        }
    }
}
