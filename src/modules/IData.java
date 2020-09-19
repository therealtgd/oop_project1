package modules;

public interface IData {

    int getId();

    void setId(int id);

    @Override
    String toString();

    String toFileString();

}
