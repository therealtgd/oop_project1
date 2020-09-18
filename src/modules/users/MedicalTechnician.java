package modules.users;

import modules.entities.MyNotification;
import modules.utils.MyPassword;

import java.util.List;

public class MedicalTechnician extends Employee {

    private int homeVisits;
    private List<MyNotification> notifications;

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

     public MedicalTechnician(int id, String username, String name, String surname, MyPassword password, double salaryBase, int experience, int homeVisits) {
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

    public List<MyNotification> getNotifications() {
        return notifications;
    }

    @Override
    public double getBonus() {
        return getBonusBase() * homeVisits;
    }

    public void addNotification(MyNotification n) {
        notifications.add(n);
    }

    @Override
    public String toString() {
        return "MedicinskiTehničar [" + super.toString() + ']';
    }

    @Override
    public String toFileString() {
        StringBuilder ids = new StringBuilder();
        for (MyNotification n: notifications) {
            ids.append(n.getId()).append(";");
        }
        return super.toFileString() + ',' + homeVisits + "," + ids;
    }

}
