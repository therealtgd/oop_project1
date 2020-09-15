package view;

import modules.users.Laborant;

import javax.swing.*;

public class LaborantFrame extends JFrame {

    private Laborant laborant;

    public LaborantFrame(Laborant l) {
        this.laborant = l;
        laborantFrame();
    }

    private void laborantFrame() {
        this.setTitle("Meni laboranta");
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initLaborantGUI();
        this.setVisible(true);
    }

    private void initLaborantGUI() {
        JMenuBar mainMenu = new JMenuBar();

        JMenu profileMenu = new JMenu("Profil");
        JMenuItem passwordItem = new JMenuItem("Promeni šifru");

        profileMenu.add(passwordItem);

        JMenu analysisMenu = new JMenu("Analize");
        JMenuItem availableAnalysisItem = new JMenuItem("Dostupne analize");

        analysisMenu.add(availableAnalysisItem);

        mainMenu.add(profileMenu);
        mainMenu.add(analysisMenu);
        this.setJMenuBar(mainMenu);
    }
}
