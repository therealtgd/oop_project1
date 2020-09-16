package modules.DTO;


public class MedicalTechnicianDTO extends EmployeeDTO {

    int homeVisits;

    public MedicalTechnicianDTO(EmployeeDTO employeeDTO) {
        this.homeVisits = 0;
    }

    public MedicalTechnicianDTO(String username, String name, String surname, double salaryBase, int experience) {
        super(username, name, surname, salaryBase, experience);
        this.homeVisits = 0;
    }

    public int getHomeVisits() {
        return homeVisits;
    }
}
