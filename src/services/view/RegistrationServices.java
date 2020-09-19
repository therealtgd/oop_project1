package services.view;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.DTO.*;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.users.Patient;
import modules.users.User;
import services.Services;
import services.utils.Builder;
import services.view.exceptions.RegistrationException;

import java.util.ArrayList;

public class RegistrationServices extends Services {

    private ArrayList<UserDatabase> userDatabase;


    public RegistrationServices(DatabaseHandler dH) {
        super(dH);
        this.userDatabase = getdH().getUserDatabase().getUsers();
    }

    public void registerPatient(PatientDTO pDTO) throws RegistrationException {
        checkUsername(pDTO);
        Patient p = Builder.buildPatient(pDTO);
        getdH().getUserDatabase().getPatientDatabase().addData(p);
    }

    public void registerMedicalTechnician(MedicalTechnicianDTO mTDTO) throws RegistrationException {
        checkUsername(mTDTO);
        MedicalTechnician mT = Builder.buildMedicalTechnician(mTDTO);
        getdH().getUserDatabase().getMedTechnicianDatabase().addData(mT);
    }

    public void registerLaborant(LaborantDTO lDTO) throws RegistrationException {
        checkUsername(lDTO);
        Laborant l = Builder.buildLaborant(lDTO);
        getdH().getUserDatabase().getLaborantDatabase().addData(l);
    }

    private void checkUsername(AccountDTO pDTO) throws RegistrationException {
        for (UserDatabase<User> uDb: userDatabase) {
            for (User u: uDb.getData()) {
                if (u.getUsername().equals(pDTO.getUsername())) {
                    throw new RegistrationException("Korisničko ime je zauzeto.\nPokušajte ponovo.");
                }
            }
        }
    }

    private EmployeeDTO getEmployeeDTO(String username, String name, String surname, String salary, String experience) {
        return new EmployeeDTO(username, name, surname, getSalValue(salary), getXpValue(experience));

    }

    private int getXpValue(String experience) {
        int xpValue;
        try {
            xpValue = Integer.parseInt(experience);
        } catch (NumberFormatException e) {
            xpValue = 0;
        }
        return xpValue;
    }

    private double getSalValue(String salary) {
        double salValue;
        try {
            salValue = Double.parseDouble(salary);
        } catch (NumberFormatException e) {
            salValue = 0.0;
        }
        return salValue;
    }

    public LaborantDTO getLaborantDTO(String username, String name, String surname, String salary, String experience, String qualifications, ArrayList<String> specializations) {
        return new LaborantDTO(getEmployeeDTO(username, name, surname, salary, experience), qualifications, specializations);
    }

    public MedicalTechnicianDTO getMedicalTechnicianDTO(String username, String name, String surname, String salary, String experience) {
        return new MedicalTechnicianDTO(getEmployeeDTO(username, name, surname, salary, experience));
    }

    public PatientDTO getPatientDTO(String username, String name, String surname, String LBO, String address, String phone, String gender) {
        return new PatientDTO(username, name, surname, LBO, address, phone, gender);
    }
}
