package services.utils;

import modules.DTO.PatientAccountDTO;
import modules.users.Patient;
import modules.utils.MyPassword;

public class Builder {

    public static Patient buildPatient(PatientAccountDTO pDTO) {
        MyPassword pass = PasswordUtils.generateRandomPass(pDTO.getPassword());
        Patient p = new Patient(pDTO.getUsername(), pDTO.getName(), pDTO.getSurname(), pass, pDTO.getLBO());
        p.setAddress(pDTO.getAddress());
        p.setPhone(pDTO.getPhone());
        p.setGender(pDTO.getGender());
        return p;
    }

}
