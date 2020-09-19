package view.utils;

import modules.entities.Analysis;
import modules.entities.AnalysisGroup;
import modules.utils.Range;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisGroupPanel extends JPanel {

    private String title;
    private AnalysisGroup analysisGroup;
    private Map<Analysis, JCheckBox> btnMap;

    public AnalysisGroupPanel(String title, AnalysisGroup analysisGroup) {
        this.title = title;
        this.analysisGroup = analysisGroup;
        formBtnMap();
        initGUI();
    }

    public Map<Analysis, JCheckBox> getBtnMap() {
        return btnMap;
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 3", "[sg 1]10[sg 2]10[]");
        setLayout(layout);

        Border border = new TitledBorder(title);
        setBorder(border);

        add(new JLabel("Izbor"), "pushx, growx");
        add(new JLabel("Tip"), "pushx, growx");
        add(new JLabel("Cena (RSD)"), "pushx, growx");
        addButtons();

    }

    private void formBtnMap() {
        btnMap = new HashMap<>();
        for (Analysis a : analysisGroup.getAnalyses()) {
            btnMap.put(a, new JCheckBox());
        }
    }

    private void addButtons() {
        for (Map.Entry<Analysis, JCheckBox> entry : btnMap.entrySet()) {
            add(entry.getValue(), "pushx, growx");
            add(new JLabel(entry.getKey().getType()), "pushx, growx");
            add(new JLabel(String.valueOf(entry.getKey().getCost())), "pushx, growx");
        }
    }

    public List<Analysis> getSelection() {
        List<Analysis> retVal = new ArrayList<>();
        for (Map.Entry<Analysis, JCheckBox> entry : btnMap.entrySet()) {
            if (entry.getValue().isSelected())
                retVal.add(entry.getKey());
        }
        return retVal;
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
//        Analysis analysis = new Analysis(0, "Alergologija", "PHADIATOP INFANT - ALERGIJA", new Range(10, 20), "mol/l", 1500);
//        Analysis analysis2 = new Analysis(0, "Alergologija", "PHADIATOP INFANT - ALERGIJA", new Range(10, 20), "mol/l", 1500);
//        Analysis analysis3 = new Analysis(0, "Alergologija", "PHADIATOP INFANT - ALERGIJA", new Range(10, 20), "mol/l", 1500);
//        Analysis analysis4 = new Analysis(0, "Alergologija", "PHADIATOP INFANT - ALERGIJA", new Range(10, 20), "mol/l", 1500);
//        List<Analysis> analyses = new ArrayList<>();
//        analyses.add(analysis);
//        analyses.add(analysis2);
//        analyses.add(analysis3);
//        analyses.add(analysis4);

//        f.add(new AnalysisGroupPanel("Alergologija", analyses));
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

}
