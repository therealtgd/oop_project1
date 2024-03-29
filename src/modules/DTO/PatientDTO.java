package modules.DTO;

public class PatientDTO extends AccountDTO {
    private String LBO;
    private String address;
    private String phone;
    private String gender;

    public PatientDTO(String username, String name, String surname, String LBO, String address, String phone, String gender) {
        super(username, name, surname);
        this.LBO = LBO;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    public PatientDTO(String username, String password, String name, String surname, String LBO) {
        super(username, password, name, surname);
        this.LBO = LBO;
        this.address = "";
        this.phone = "";
        this.gender = "";

    }

    public String getLBO() {
        return LBO;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
