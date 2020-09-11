package modules.users;

public class Patient extends User {

    private enum Gender {
        MUŠKO,
        ŽENSKO
    }

    private String LBO;
    private Gender gender;
    private String phone;
    private String address;

    public Patient(int id, String username, String name, String surname, String password, String LBO) {
        super(id, username, name, surname, password);
        this.LBO = LBO;
    }

    public Patient(int id, String username, String name, String surname, String password, String LBO, String gender,
                   String phone, String address) {
        super(id, username, name, surname, password);
        this.LBO = LBO;
        this.gender = Gender.valueOf(gender);
        this.phone = phone;
        this.address = address;
    }

    public String getLBO() {
        return LBO;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Pacjent [LBO=" + LBO + ", pol=" + gender + ", telefon='" + phone + ", adresa='" + address + "]";
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + LBO + "," + gender + "," + phone + "," + address;
    }
}
