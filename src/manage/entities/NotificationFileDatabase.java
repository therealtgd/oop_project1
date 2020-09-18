package manage.entities;

import manage.FileDatabase;
import modules.entities.MyNotification;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificationFileDatabase extends FileDatabase<MyNotification> {

    public NotificationFileDatabase(String filename) {
        super(filename);
        loadData();
    }

    @Override
    public boolean loadData() {
        setData(new ArrayList<>());
        try {
            BufferedReader br = new BufferedReader(new FileReader(getFile()));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                MyNotification n = new MyNotification(Integer.parseInt(tokens[0]), tokens[1], LocalDateTime.parse(tokens[2]), tokens[3]);
                List<MyNotification> data = getData();
                data.add(n);
                setData(data);
            }
            br.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
