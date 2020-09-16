package modules.DTO;

public class EmployeeDTO extends AccountDTO {

    double salaryBase;
    int experience;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String username, String name, String surname, double salaryBase, int experience) {
        super(username, name, surname);
        this.salaryBase = salaryBase;
        this.experience = experience;
    }

    public double getSalaryBase() {
        return salaryBase;
    }

    public int getExperience() {
        return experience;
    }
}
