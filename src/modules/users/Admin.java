package modules.users;

import modules.utils.MyPassword;

public class Admin extends User {

    public Admin(int id, String username, String name, String surname) {
        super(id, username, name, surname);
    }

    public Admin(int id, String username, String name, String surname, MyPassword password) {
        super(id, username, name, surname, password);
    }

    public Admin(String username, String name, String surname, MyPassword pass) {
        super(username, name, surname, pass);
    }

    @Override
    public String toString() {
        return "Admin [" + super.toString() + "]";
    }
}
