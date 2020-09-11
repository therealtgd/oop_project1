package modules.users;

import modules.Data;

public abstract class User extends Data {

    private String username;
    private String name;
    private String surname;
    private String password;

    public User(int id, String username, String name, String surname, String password) {
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

    @Override
    public String toString() {
        return "korisnicko ime=" + username + ", ime=" + name + ", prezime=" + surname;
    }

    public String toFileString() {
        return super.toString() + ',' + username + ',' + name + ',' + surname;
     }

}
