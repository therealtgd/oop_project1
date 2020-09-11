package modules.users;

public class Admin extends User {

    public Admin(int id, String username, String name, String surname, String password) {
        super(id, username, name, surname, password);
    }

    @Override
    public String toString() {
        return "Admin [" + super.toString() + "]";
    }
}
