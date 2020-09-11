package modules.users;

public class MedicalTechnician extends Employee {

    private int homeVisits;

    public MedicalTechnician(int id, String username, String name, String surname, String password) {
        super(id, username, name, surname, password);
    }

    public MedicalTechnician(int id, String username, String name, String surname, String password, double salaryBase, int experience) {
        super(id, username, name, surname, password, salaryBase, experience);
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

    public void PrintAnalysis(){};

    @Override
    public String toString() {
        return "MedicinskiTehničar [" + super.toString() + ']';
    }

    @Override
    public String toFileString(){
        return super.toFileString() + ',' + homeVisits;
    }

}
