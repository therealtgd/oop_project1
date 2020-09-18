package services.utils;

import modules.DTO.*;
import modules.entities.AnalysisRequest;
import modules.users.Employee;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import modules.users.Patient;
import modules.utils.MyPassword;

public class Builder {

    public static Patient buildPatient(PatientDTO pDTO) {
        MyPassword pass = PasswordUtils.generateRandomPass(pDTO.getPassword());
        Patient p = new Patient(pDTO.getUsername(), pDTO.getName(), pDTO.getSurname(), pass, pDTO.getLBO());
        if (!pDTO.getAddress().equals(""))
            p.setAddress(pDTO.getAddress());
        if (!pDTO.getPhone().equals(""))
            p.setPhone(pDTO.getPhone());
        if (pDTO.getGender() != null)
            p.setGender(pDTO.getGender());
        return p;
    }

    public static MedicalTechnician buildMedicalTechnician(MedicalTechnicianDTO mTDTO) {
        return new MedicalTechnician(buildEmployee(mTDTO));
    }

    public static Laborant buildLaborant(LaborantDTO lDTO) {
        return new Laborant(buildEmployee(lDTO), lDTO.getQualification(), lDTO.getSpecialization());
    }

    private static Employee buildEmployee(EmployeeDTO employeeDTO) {
        MyPassword pass = PasswordUtils.generateRandomPass(employeeDTO.getPassword());
        return new Employee(employeeDTO.getUsername(), employeeDTO.getName(), employeeDTO.getSurname(), pass, employeeDTO.getSalaryBase(), employeeDTO.getExperience()) {
            @Override
            public double getBonus() {
                return 0;
            }
        };
    }

    public static AnalysisRequest buildAnalysis(AnalysisRequestDTO aRDTO) {
        return new AnalysisRequest(aRDTO.getPatient(), aRDTO.getAnalyses(), aRDTO.getHomeVisit());
    }
}
