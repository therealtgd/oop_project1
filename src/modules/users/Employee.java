package modules.users;

public abstract class Employee extends User {

    private double salaryBase;
    private int experience;
    private double bonusBase;

    public Employee(int id, String username, String name, String surname, String password) {
        super(id, username, name, surname, password);
    }

    public Employee(int id, String username, String name, String surname, String password, double salaryBase, int experience) {
        super(id, username, name, surname, password);
        this.salaryBase = salaryBase;
        this.experience = experience;
    }

    public double getSalaryBase() {
        return salaryBase;
    }

    public int getExperience() {
        return experience;
    }

    public double getBonusBase() {
        return bonusBase;
    }

    public void setBonusBase(double bonusBase) {
        this.bonusBase = bonusBase;
    }

    public abstract double getBonus();

    @Override
    public String toString() {
        return super.toString() + ", osnova=" + salaryBase + ", iskustvo=" + experience + ", bonus=" + bonusBase;
    }

    @Override
    public String toFileString(){
        return super.toFileString() + ',' + salaryBase + ',' + experience + ',' + bonusBase;
    }
}
