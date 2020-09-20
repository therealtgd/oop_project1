package view.patient;

import com.sun.xml.internal.messaging.saaj.soap.JpegDataContentHandler;
import modules.entities.Analysis;
import modules.entities.Measurement;
import net.miginfocom.swing.MigLayout;
import view.laborant.AnalysisTablePanel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinishedAnalysisFrame extends JFrame {

    private Map<Analysis, Measurement> map;
    Map<String, ArrayList<Analysis>> analysisGroup;

    public FinishedAnalysisFrame(Map<Analysis, Measurement> map) {
        this.map = map;
        this.analysisGroup = formAnalysisGroup();
        this.setTitle("Labaratoriji");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initGUI();
        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap", "[sg 1]");
        setLayout(layout);
        initTableGUI();
    }

    private void initTableGUI() {
        for (Map.Entry<String, ArrayList<Analysis>> entry : analysisGroup.entrySet()) {
            JPanel p = new JPanel();
            Border b = new TitledBorder(entry.getKey());
            p.setBorder(b);
            p.add(new FinishedAnalysisTablePanel(getAnalysisMeasurements()));
            add(p, "pushx, growx");
        }
    }

    private Map<String, ArrayList<Analysis>> formAnalysisGroup() {
        Map<String, ArrayList<Analysis>> retVal = new HashMap<>();
        for (Analysis a : map.keySet()) {
            retVal.put(a.getAnalysisGroup(), new ArrayList<>());
            retVal.get(a.getAnalysisGroup()).add(a);
        }
        return retVal;
    }

    private Map<Analysis, Measurement> getAnalysisMeasurements() {
        Map<Analysis, Measurement> retVal = new HashMap<>();
        for (Map.Entry<String, ArrayList<Analysis>> entry : analysisGroup.entrySet()) {
            for (Analysis a : entry.getValue()) {
                retVal.put(a, map.get(a));
            }
        } return retVal;
    }

}
