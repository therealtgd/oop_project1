package modules.manage;

import modules.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class FileDatabase implements Database<Data> {

    private List<Data> data;
    private String file;

    public FileDatabase() {
    }

    public FileDatabase(String file) {
        this.data = new ArrayList<Data>();
        this.file = file;
    }

    public List<Data> getData() {
        return data;
    }

    public String getFile() {
        return file;
    }

    public void addData(Data d) {
        if (data.isEmpty()) {
            d.setId(0);
        } else {
            d.setId(data.get(data.size() - 1).getId() + 1);
        }
        data.add(d);
        saveData();
    }

    public abstract boolean loadData();

    public boolean saveData() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(this.file, false));
            for (Data d : data) {
                pw.println(d.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Data getById(int id) {
        Data retVal = null;
        for (int i = 0; i < data.size(); i++) {
            Data d = data.get(i);
            if (d.getId() == id) {
                retVal = d;
                break;
            }
        }
        return retVal;
    }

}
