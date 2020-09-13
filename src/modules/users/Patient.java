package modules.users;

import modules.utils.MyPassword;

public class Patient extends User {

    private enum Gender {
        MUŠKO,
        ŽENSKO
    }

    private String LBO;
    private Gender gender;
    private String phone;
    private String address;

    public Patient(String username, String name, String surname, MyPassword password, String LBO) {
        super(username, name, surname, password);
        this.LBO = LBO;
        this.gender = null;
        this.phone = null;
        this.address = null;
    }

    public Patient(int id, String username, String name, String surname, MyPassword password, String LBO) {
        super(id, username, name, surname, password);
        this.LBO = LBO;
        this.gender = null;
        this.phone = null;
        this.address = null;
    }

    public Patient(int id, String username, String name, String surname, MyPassword password, String LBO, String gender,
                   String phone, String address) {
        super(id, username, name, surname);
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

    public void setGender(String gender) {
        this.gender = Gender.valueOf(gender);
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Pacjent [LBO=" + LBO + ", pol=" + gender + ", telefon='" + phone + ", adresa='" + address + "]";
    }

    @Override
    public String toFileString() {
//        String retval = super.toFileString() + "," + LBO;
//        if (gender != null) { retval += "," + gender;}
//        if (phone != null) { retval += "," + phone;}
//        if (address != null) {retval += "," + address}
//        return retval;
        return super.toFileString() + "," + LBO + "," + gender + "," + phone + "," + address;
    }
}
