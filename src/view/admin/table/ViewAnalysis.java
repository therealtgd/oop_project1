package view.admin.table;

import manage.DatabaseHandler;
import view.laborant.AnalysisTablePanel;

import javax.swing.*;
import java.awt.*;

public class ViewAnalysis extends JDialog {

    DatabaseHandler dH;

    public ViewAnalysis(DatabaseHandler dH) {
        this.dH = dH;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon2.png"));
        this.setTitle("Labaratoriji");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initGUI();
        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initGUI() {
        add(new AnalysisTablePanel(dH, "Sve analize", dH.getEntityDatabase().getAnalysisDatabase().getData()), BorderLayout.CENTER);
    }

}
