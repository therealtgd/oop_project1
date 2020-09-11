package modules.DTO;

public class PatientAccountDTO extends AccountDTO {
    private int LBO;

    public PatientAccountDTO() {
    }

    public PatientAccountDTO(String username, String name, String surname, int LBO) {
        super(username, name, surname);
        this.LBO = LBO;
    }

    public int getLBO() {
        return LBO;
    }
}
