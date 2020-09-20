package view.laborant;

import manage.DatabaseHandler;
import modules.users.Employee;
import modules.users.Laborant;
import modules.utils.MyPassword;
import services.utils.PasswordUtils;
import view.ProfileMenu;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class LaborantFrame extends JFrame {

    private Laborant laborant;
    private DatabaseHandler dH;
    private AnalysisView analysisView;

    public LaborantFrame(Laborant laborant, DatabaseHandler dH) {
        this.laborant = laborant;
        this.dH = dH;
        this.analysisView = new AnalysisView(dH, laborant);
        laborantFrame();
    }

    private void laborantFrame() {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon2.png"));
        this.setTitle("Meni laboranta");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initLaborantGUI();
        this.setVisible(true);
    }

    private void initLaborantGUI() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu profileMenu = new ProfileMenu(laborant);

        JMenu analysisMenu = new JMenu("Analize");
        JMenuItem availableAnalysisItem = new JMenuItem("Dostupne analize");

        analysisMenu.add(availableAnalysisItem);

        mainMenu.add(profileMenu);
        mainMenu.add(analysisMenu);
        this.setJMenuBar(mainMenu);
        add(analysisView);
    }

    public static void main(String[] args) {
        MyPassword mP2 = PasswordUtils.generateRandomPass("ZMxOAS5WD0");
        List<String> specs = Arrays.asList("Alergologija", "Genetika", "Imunologija", "Imunohemija", "Serologija", "Hematologija");

        Employee e = new Employee(0, "laborant_glavni", "Laboslav", "Labovski", mP2, 100.10, 12) {
            @Override
            public double getBonus() {
                return 0;
            }
        };
        Laborant l = new Laborant(e, "SSS", specs);
//        1,laborant_glavni,Laboslav,Labovski,mONbvB2if+DNaMW7rWHmS7NdGyOAmHOH8sfp1GgWZVx941nyM/DFNwhbS7AQq48nzGwvzTiCkWa6NNC7ml0Pyg==MyPassII5ZiYTwuA8jjmPEID8i++2fLg9/drsonUcNM3isEJkybb5wF0NKoZzdgmXw8rL5y+Q9hB4F31T7mg/eKaEJN0T9EEGei7n4FyKavdUNlAoO6N3lbcm/lPMBTjnByXBe1074kr+FRJBki38b0xQOwtpf/rX2HOkGL3LDRzhqVTdR0LVXEiDhdi3uEATyemoX07gvn4+KNFGMaDxuXRFVQM4dkf5xhinMgw2UkRiSnCXxN2WR0ZEMTDj8vRbIC4FwMPlCLqF/PauME4jevAuq604pscbW+UMVK3O6ytVtWMQHozI78YLx6lNuGx8rm/2AZm1s93xR3zF/a8Nj3JyI5AkIwOMiwFcp1uqWp2Ngf40kTPf7T/JWDpHWDkhO2+1ls0OEuT8MslmcUXsvdpNFrLRzN1f/XhC1wv6qe87PeHWNwIz6/oE7AF2q1Qis/zMiBO/R458XCQNqoPlYH+Dcb0pj7Fe+bKKp+RhFxBc5OWTsVEA0CmqoMmn4SGPaBKLJvYK57gOQFr2WM1xfpZqSWgwdNfYF7DZVZ6b3fos9ZgaEr2z8zXp/UtFhKH91sQN/k0zWEWCheDIbZkQCXGuOQdWtDimajlPsZh2OBN0gcPDGGl1p1bH6+Hm2T0HJ0+dKAl2AGKETbHFtxkrCXjWgcaKOwwu6bwBOPt0Qn2d6J8Y=,150.25,15,Master akademske studije (180+120 ili 240+60 ESPB),Alergologija;Genetika;Imunologija;
        DatabaseHandler dH = new DatabaseHandler();
        LaborantFrame lF = new LaborantFrame(l, dH);
    }

}
