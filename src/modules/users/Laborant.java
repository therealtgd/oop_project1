package modules.users;

import modules.entities.Specialization;

import java.util.List;

public class Laborant extends Employee {

    private String qualification;
    private List<Specialization> specializations;

    public Laborant(int id, String username, String name, String surname, String password) {
        super(id, username, name, surname, password);
        setBonusBase(500);
    }

    public Laborant(int id, String username, String name, String surname, String password, double salaryBase, int experience) {
        super(id, username, name, surname, password, salaryBase, experience);
        setBonusBase(500);
    }

    public Laborant(int id, String username, String name, String surname, String password, double salaryBase, int experience, String qualification) {
        super(id, username, name, surname, password, salaryBase, experience);
        this.qualification = qualification;
        setBonusBase(500);
    }

    public String getQualification() {
        return qualification;
    }

    public List getSpecializations() {
        return specializations;
    }

    public void addSpecialization(Specialization s){
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

}
