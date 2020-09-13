package manage;


import java.util.List;

public interface Database<T> {

    List<T> getData();
    boolean loadData();
    boolean saveData();
    T getById(int id);
    void addData(T t);
}
