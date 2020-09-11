package modules.manage;


public interface Database {

    boolean loadData();
    boolean saveData();
    Object getById(int id);

}
