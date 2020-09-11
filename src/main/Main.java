package main;

import modules.users.Admin;
import modules.users.Laborant;
import modules.users.MedicalTechnician;

public class Main {

    public static void main(String[] args) {
        Admin a = new Admin("admin", "Admin", "Adminic", "Sifra12");
        MedicalTechnician mT = new MedicalTechnician("admin", "Admin", "Adminic", "Sifra12");
        Laborant l = new Laborant("admin", "Admin", "Adminic", "Sifra12");
        System.out.println(a);
        System.out.println(mT);
        System.out.println(l);
    }


}
