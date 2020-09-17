package view.validators;

import modules.DTO.EmployeeDTO;
import modules.DTO.LaborantDTO;
import modules.DTO.MedicalTechnicianDTO;
import modules.DTO.PatientDTO;
import modules.users.User;
import services.utils.PasswordUtils;
import view.validators.exceptions.LoginException;

import java.util.*;

public class Validator {


    private static final String ERR_FIELD_REQUIRED = "Polje obavezno";
    private static final String ERR_USERNAME_INVALID = "Korisničko ime mora sadržati 5-20 karaktera, može sadržati '.' i '_'";
    private static final String ERR_PASS_INVALID = "Šifra mora sadržati minimalno 8 karaktera, veliko i malo slovo i broj";
    private static final String ERR_LBO_INVALID = "LBO neispravan";
    private static final String ERR_ADDRESS_INVALID = "Unesite adresu u formatu 'ulica br, grad'";
    private static final String ERR_PHONE_INVALID = "Unesite telefon u obliku '+381xxxxxxxxx'";
    private static final String ERR_SALARY_INVALID = "Uneta platna osnova je neispravna";
    private static final String ERR_WRONG_PASSWORD = "Šifra nije tačna";
    private static final String ERR_SAME_PASSWORD = "Nova šifra ne može biti ista kao stara";
    private static final String ERR_NO_MATCH_PASSWORD = "Šifre se ne podudaraju";

    public static void validateLogin(String username, String password) throws LoginException {
        if (username.isEmpty() && password.isEmpty()) {
            throw new LoginException("Unesite korisničko ime i šifru.");
        } else if (username.isEmpty()) {
            throw new LoginException("Unesite korisničko ime.");
        } else if (password.isEmpty()) {
            throw new LoginException("Unesite šifru.");
        }
    }

    public static Map<String, String> validatePatientRegistration(PatientDTO pDTO) {

        Map<String, String> errCodes = new HashMap<>();
        if (pDTO.getUsername().equals(""))
            errCodes.put("username", ERR_FIELD_REQUIRED);
        else if (!pDTO.getUsername().matches("^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"))
            errCodes.put("username", ERR_USERNAME_INVALID);
        if (pDTO.getPassword().isEmpty() || pDTO.getPassword().equals(""))
            errCodes.put("password", ERR_FIELD_REQUIRED);
        else if (!pDTO.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"))
            errCodes.put("password", ERR_PASS_INVALID);
        if (pDTO.getName().equals(""))
            errCodes.put("name", ERR_FIELD_REQUIRED);
        if (pDTO.getSurname().equals(""))
            errCodes.put("surname", ERR_FIELD_REQUIRED);
        if (pDTO.getLBO().isEmpty())
            errCodes.put("LBO", ERR_FIELD_REQUIRED);
        else if (!pDTO.getLBO().matches("[0-9]{8}"))
            errCodes.put("LBO", ERR_LBO_INVALID);
        if (!pDTO.getAddress().isEmpty() && !pDTO.getAddress().matches("([A-z]+\\s)+\\d+,+\\s+([A-z])+"))
            errCodes.put("address", ERR_ADDRESS_INVALID);
        if (!pDTO.getPhone().isEmpty() && !pDTO.getPhone().matches("(\\+381)?(\\s|-)?6(([0-6]|[8-9])\\d{7}|(77|78)\\d{6})"))
            errCodes.put("phone", ERR_PHONE_INVALID);
        return errCodes;

    }

    public static Map<String, String> validateMedicalTechnicianRegistration(MedicalTechnicianDTO mTDTO) {
        return validateEmployeeRegistration(mTDTO);
    }

    public static Map<String, String> validateLaborantRegistration(LaborantDTO lDTO) {
        Map<String, String> errCodes = validateEmployeeRegistration(lDTO);
        return errCodes;
    }

    private static Map<String, String> validateEmployeeRegistration(EmployeeDTO employeeDTO) {
        Map<String, String> errCodes = new HashMap<>();
        if (employeeDTO.getUsername().equals(""))
            errCodes.put("username", ERR_FIELD_REQUIRED);
        else if (!employeeDTO.getUsername().matches("^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$"))
            errCodes.put("username", ERR_USERNAME_INVALID);
        if (employeeDTO.getName().equals(""))
            errCodes.put("name", ERR_FIELD_REQUIRED);
        if (employeeDTO.getSurname().equals(""))
            errCodes.put("surname", ERR_FIELD_REQUIRED);
        if (employeeDTO.getSalaryBase() == 0)
            errCodes.put("salaryBase", ERR_SALARY_INVALID);
        return errCodes;
    }

    public static  Map<String, String> validatePasswordChange(User user, String currPass, String newPass, String confirmPass) {
        Map<String, String> errCodes = new HashMap<>();
        if (currPass.equals(""))
            errCodes.put("currPass", ERR_FIELD_REQUIRED);
        if(!PasswordUtils.verifyPassword(currPass, user.getPassword().getKey(), user.getPassword().getSalt()))
            errCodes.put("currPass", ERR_WRONG_PASSWORD);
        else if(currPass.equals(newPass))
            errCodes.put("currPass", ERR_SAME_PASSWORD);
        if (newPass.equals(""))
            errCodes.put("newPass", ERR_FIELD_REQUIRED);
        else if (!newPass.equals(confirmPass))
            errCodes.put("newPass", ERR_NO_MATCH_PASSWORD);
        else if (!newPass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$"))
            errCodes.put("newPass", ERR_PASS_INVALID);
        if (confirmPass.equals(""))
            errCodes.put("confirmPass", ERR_FIELD_REQUIRED);
        return errCodes;
    }
}
