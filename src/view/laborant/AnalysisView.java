package view.laborant;


import manage.DatabaseHandler;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import modules.users.Laborant;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnalysisView extends JPanel {

    private AnalysisTablePanel analysisPanel;
    private AnalysisRequestTablePanel activeAnalysisPanel;
    private DatabaseHandler dH;
    private Laborant laborant;

    public AnalysisView(DatabaseHandler dH, Laborant laborant) {
        this.dH = dH;
        this.laborant = laborant;
        this.analysisPanel = analysisPanel();
        this.activeAnalysisPanel = activeAnalysisPanel();
        MigLayout layout = new MigLayout("fill", "[]");
        setLayout(layout);
        notificationView();
    }

    private void notificationView() {
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Svi tipovi analiza", analysisPanel);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.add("Dostupne analize", activeAnalysisPanel);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        add(tabbedPane, "push, grow");
    }

    private AnalysisTablePanel analysisPanel() {
        return new AnalysisTablePanel(dH, "Pregled svih analiza", dH.getEntityDatabase().getAnalysisDatabase().getData());
    }

    private AnalysisRequestTablePanel activeAnalysisPanel() {
        return new AnalysisRequestTablePanel(dH, "Pregled svih zahtjeva", getAnalysisRequestData(), laborant);

    }

    public List<AnalysisRequest> getAnalysisRequestData() {
        List<AnalysisRequest> aList = new ArrayList<>();
        for (AnalysisRequest aR : dH.getEntityDatabase().getAnalysisRequestDatabase().getData()) {
            for (Map.Entry<Analysis, Measurement> entry : aR.getAnalysisMeasurementMap().entrySet()) {
                if (laborant.getSpecializations().contains(entry.getKey().getAnalysisGroup())) {
                    if (entry.getValue().getValue() == 0.0) {
                        aList.add(aR);
                        break;
                    }
                }
            }
        }
        return aList;
    }

    public void refresh() {
        analysisPanel.refresh();
    }

}
