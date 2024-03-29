package manage;

import modules.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public abstract class FileDatabase<T extends Data> implements Database<T> {

    private List<T> data;
    private String file;

    public FileDatabase() {
        loadData();
    }

    public FileDatabase(String file) {
        this.data = new ArrayList<T>();
        this.file = file;
    }

    public List<T> getData() {
        return data;
    }

    public String getFile() {
        return file;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public void addData(T d) {
        if (data.isEmpty()) {
            d.setId(0);
        } else {
            d.setId(getMaxId() + 1);
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

    public T getById(int id) {
        T retVal = null;
        for (T d : data) {
            if (d.getId() == id) {
                retVal = d;
                break;
            }
        }
        return retVal;
    }

    @Override
    public void remove(int id) {
        data.removeIf(o -> o.getId() == id);
        saveData();
    }

    @Override
    public void edit(T editObj) {

    }

    @Override
    public int getMaxId() {
        int max = 0;
        for (T d: data) {
            if (d.getId() > max) {
                max = d.getId();
            }
        } return max;
    }
}
