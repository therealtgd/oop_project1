package view.laborant;


import javax.swing.*;
import java.awt.*;

public class AvailableAnalysisFrame extends JFrame {

    private AvailableAnalysisTablePanel aATablePanel;

    public AvailableAnalysisFrame(AvailableAnalysisTablePanel aATablePanel) throws HeadlessException {
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
