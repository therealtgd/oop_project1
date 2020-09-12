package modules.users;

import modules.Data;
import modules.utils.MyPassword;

public abstract class User extends Data {

    private String username;
    private String name;
    private String surname;
    private MyPassword password;

    public User(String username, String name, String surname, MyPassword password) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public User(int id, String username, String name, String surname) {
        super(id);
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = new MyPassword();
    }

    public User(int id, String username, String name, String surname, MyPassword password) {
        super(id);
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(MyPassword password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "korisnicko ime=" + username + ", ime=" + name + ", prezime=" + surname;
    }

    public String toFileString() {
        return super.toString() + ',' + username + ',' + name + ',' + surname + password.toFileString();
     }

}
