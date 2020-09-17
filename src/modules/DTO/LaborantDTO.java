package modules.DTO;

import java.util.ArrayList;
import java.util.List;

public class LaborantDTO extends EmployeeDTO {

    String qualification;
    List<String> specialization;

    public LaborantDTO(String username, String name, String surname, double salaryBase, int experience, String qualification, ArrayList<String> specializations) {
        super(username, name, surname, salaryBase, experience);
        this.qualification = qualification;
        this.specialization = specializations;
    }

    public LaborantDTO(EmployeeDTO employeeDTO, String qualification, ArrayList<String> specialization) {
        super(employeeDTO.getUsername(), employeeDTO.getName(), employeeDTO.getSurname(), employeeDTO.getSalaryBase(), employeeDTO.getExperience());
        this.qualification = qualification;
        this.specialization = specialization;
    }

    public String getQualification() {
        return qualification;
    }

    public List<String> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(List<String> specialization) {
        this.specialization = specialization;
    }
}
