package modules.manage;


public interface Manager {

    boolean loadData();
    boolean saveData();
    Object getById(int id);

}
