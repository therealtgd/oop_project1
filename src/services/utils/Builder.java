package services.utils;

import manage.DatabaseHandler;
import modules.DTO.*;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import modules.users.*;
import modules.utils.MyPassword;

import java.util.HashMap;
import java.util.Map;

public class Builder {

    private DatabaseHandler dH;

    public Builder(DatabaseHandler dH) {
        this.dH = dH;
    }

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

    public static Admin buildAdmin(AdminDTO aDTO) {
        MyPassword pass = PasswordUtils.generateRandomPass(aDTO.getPassword());
        return new Admin(aDTO.getUsername(), aDTO.getName(), aDTO.getSurname(), pass);
    }

    public AnalysisRequest buildAnalysiRequest(AnalysisRequestDTO aRDTO) {
        Map<Analysis, Measurement> map = new HashMap<>();
        for (Analysis a: aRDTO.getAnalyses()) {
            int id = dH.getEntityDatabase().getMeasurementDatabase().getMaxId() + 1;
            Measurement m = new Measurement(id, a.getId());
            dH.getEntityDatabase().getMeasurementDatabase().addData(m);
            map.put(a, m);
        }
        return new AnalysisRequest(aRDTO.getPatient(), map, aRDTO.getHomeVisit());
    }
}
