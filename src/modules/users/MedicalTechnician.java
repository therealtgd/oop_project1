package modules.users;

import modules.utils.MyPassword;

public class MedicalTechnician extends Employee {

    private int homeVisits;

    public MedicalTechnician(int id, String username, String name, String surname) {
        super(id, username, name, surname);
        this.homeVisits = 0;
        setBonusBase(2000);
    }

    public MedicalTechnician(String username, String name, String surname, MyPassword password, double salaryBase, int experience) {
        super(username, name, surname, password, salaryBase, experience);
        this.homeVisits = 0;
        setBonusBase(2000);
    }

    public MedicalTechnician(int id, String username, String name, String surname, MyPassword password, double salaryBase, int experience) {
        super(id, username, name, surname, password, salaryBase, experience);
        this.homeVisits = 0;
        setBonusBase(2000);
    }

    public MedicalTechnician(Employee employee) {
        super(employee.getUsername(), employee.getName(), employee.getSurname(), employee.getPassword(), employee.getSalaryBase(), employee.getExperience());
        this.homeVisits = 0;
        setBonusBase(2000);
    }

    public int getHomeVisits() {
        return homeVisits;
    }

    public void setHomeVisits(int homeVisits) {
        this.homeVisits = homeVisits;
    }

    @Override
    public double getBonus() {
        return getBonusBase() * homeVisits;
    }

    public void printAnalysis(){};

    @Override
    public String toString() {
        return "MedicinskiTehničar [" + super.toString() + ']';
    }

    @Override
    public String toFileString() {
        return super.toFileString() + ',' + homeVisits;
    }

}
