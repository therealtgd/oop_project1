package services.utils;

import modules.DTO.PatientAccountDTO;
import modules.users.Patient;
import modules.utils.MyPassword;

public class Builder {

    public static Patient buildPatient(PatientAccountDTO pDTO) {
        MyPassword pass = PasswordUtils.generateRandomPass(20);
        return new Patient(pDTO.getUsername(), pDTO.getName(), pDTO.getSurname(), pass, pDTO.getLBO());
    }

}
