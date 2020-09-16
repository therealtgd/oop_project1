package modules.users;

import modules.utils.MyPassword;

import java.util.ArrayList;
import java.util.List;

public class Laborant extends Employee {

    private String qualification;
    private List<String> specializations;

    public Laborant(int id, String username, String name, String surname) {
        super(id, username, name, surname);
        setBonusBase(500);
    }

    public Laborant(int id, String username, String name, String surname, MyPassword password, double salaryBase,
                    int experience, String qualification) {
        super(id, username, name, surname, password, salaryBase, experience);
        this.qualification = qualification;
        this.specializations = new ArrayList<String>();
        setBonusBase(500);
    }

    public Laborant(Employee employee, String qualification, List<String> specializations) {
        super(employee.getUsername(), employee.getName(), employee.getSurname(), employee.getPassword(), employee.getSalaryBase(), employee.getExperience());
        this.qualification = qualification;
        this.specializations = specializations;
        setBonusBase(500);
    }

    public String getQualification() {
        return qualification;
    }

    public List<String> getSpecializations() {
        return specializations;
    }

    public void addSpecialization(String s){
        this.specializations.add(s);
    }

     @Override
    public double getBonus() {
        return getBonusBase() * specializations.size();
    }

    @Override
    public String toString() {
        return "Laborant [" + super.toString() + ", stručna sprema=" + qualification + ", specijalizacije=" + specializations + ']';
    }

    @Override
    public String toFileString() {
        String retVal = super.toFileString() + "," + qualification;
        String ids = null;
        if (!specializations.isEmpty()) {
            for (String s: specializations) {
                ids += specializations + ";";
            }
            retVal += "," + ids;
        }
        return retVal;
    }
}
