package modules;

public abstract class Data {

    private int id;

    public Data() {
    }

    public Data(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }

    public String toFileString() {
        return ""+id;
    }
}
