package modules.manage;

import modules.Data;

import java.util.List;

public interface Database<D extends Data> {

    List<Data> data = null;
    List<Data> getData();
    boolean loadData();
    boolean saveData();
    Data getById(int id);
    void addData(D d);
}
