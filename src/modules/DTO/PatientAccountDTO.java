package modules.DTO;

public class PatientAccountDTO extends AccountDTO {
    private String LBO;

    public PatientAccountDTO() {
    }

    public PatientAccountDTO(String username, String name, String surname, String LBO) {
        super(username, name, surname);
        this.LBO = LBO;
    }

    public String getLBO() {
        return LBO;
    }
}
