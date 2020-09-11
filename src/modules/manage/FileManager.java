package modules.manage;

import modules.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class FileManager<D extends Data> implements Manager{

    private List<D> data;
    private String file;

    public FileManager() {
    }

    public FileManager(String file) {
        this.data = new ArrayList<D>();
        this.file = file;
    }

    public List<D> getData() {
        return data;
    }

    public String getFile() {
        return file;
    }

    public void addData(D data) {
        this.data.add(data);
    }

    public abstract boolean loadData();

    public boolean saveData() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileWriter(this.file, false));
            for (D data : data) {
                pw.println(data.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public D getById(int id) {
        D retVal = null;
        for (int i = 0; i < data.size(); i++) {
            D d = data.get(i);
            if (d.getId() == id) {
                retVal = d;
                break;
            }
        }
        return retVal;
    }

}
