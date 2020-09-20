package view.patient;

import modules.entities.Analysis;
import modules.entities.Measurement;
import net.miginfocom.swing.MigLayout;
import view.patient.table.MeasurementTablePanel;
import view.patient.table.PatientAnalysisTablePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Map;

public class FinishedAnalysisTablePanel extends JPanel {

    protected Map<Analysis, Measurement> data;
    protected PatientAnalysisTablePanel analysisTablePanel;
    protected MeasurementTablePanel measurementTablePanel;


    public FinishedAnalysisTablePanel(Map<Analysis, Measurement> data) {
        this.data = data;
        this.analysisTablePanel = analysisTablePanel();
        this.measurementTablePanel = measurementTablePanel();
        initGUI();
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]");
        setLayout(layout);

        add(analysisTablePanel);
        add(measurementTablePanel);
    }


    private PatientAnalysisTablePanel analysisTablePanel() {
        return new PatientAnalysisTablePanel(new ArrayList<>(data.keySet()));

    }


    private MeasurementTablePanel measurementTablePanel() {
        return new MeasurementTablePanel(new ArrayList<>(data.values()));
    }

    public void refresh() {
        analysisTablePanel.refresh();
        measurementTablePanel.refresh();
    }

}
