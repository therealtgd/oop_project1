package view.laborant;


import javax.swing.*;
import java.awt.*;

public class AvailableAnalysisFrame extends JFrame {

    private AnalysisTablePanel aATablePanel;

    public AvailableAnalysisFrame(AnalysisTablePanel aATablePanel) throws HeadlessException {
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon2.png"));
        this.aATablePanel = aATablePanel;
        this.setTitle("Labaratoriji");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initGUI();
        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initGUI() {
        add(aATablePanel, BorderLayout.CENTER);
    }
}
