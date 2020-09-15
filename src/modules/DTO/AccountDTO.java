package modules.DTO;

public class AccountDTO {

    private String username;
    private String password;
    private String name;
    private String surname;

    public AccountDTO() {
    }

    public AccountDTO(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public AccountDTO(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
