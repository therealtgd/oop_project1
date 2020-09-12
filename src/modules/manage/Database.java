package modules.manage;

import modules.Data;

public interface Database<D extends Data> {

    boolean loadData();
    boolean saveData();
    D getById(int id);
    void addData(D d);
}
