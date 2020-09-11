package modules.DTO;

public class AccountDTO {

    private String name;
    private String surname;
    private int LBO;

    public AccountDTO() {
    }

    public AccountDTO(String name, String surname, int LBO) {
        this.name = name;
        this.surname = surname;
        this.LBO = LBO;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getLBO() {
        return LBO;
    }
}
