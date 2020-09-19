package view.medicalTechnician;

import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalysisRequestPanel extends JPanel {

    private AnalysisRequest aR;

    public AnalysisRequestPanel(AnalysisRequest aR) {
        this.aR = aR;
        initGUI();
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap", "[sg1]");
        setLayout(layout);
//        Border border = new TitledBorder("Analize");
//        setBorder(border);

        for (Analysis a : aR.getAnalyses()) {
            JPanel p = new JPanel();
            p.setLayout(new MigLayout("wrap 2", "[sg1][]"));
            Border b = new TitledBorder("");
            p.setBorder(b);
            for (Map.Entry<String, Object> entry : a.getParameters().entrySet()) {
                if (!(entry.getValue() instanceof ArrayList)) {
                    p.add(new JLabel(entry.getKey()));
                    p.add(new JLabel(entry.getValue().toString()));
                }
            }
            add(p, "pushx, growx");
        }
    }
}
