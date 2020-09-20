package main;

import manage.DatabaseHandler;
import view.MainFrame;

public class Main {

    public static void main(String[] args) {
        System.out.println("Učitavanje podataka");
        DatabaseHandler dH = new DatabaseHandler();

        System.out.println("Pokretanje aplikacije");
        MainFrame mf = new MainFrame(dH);
    }
}
