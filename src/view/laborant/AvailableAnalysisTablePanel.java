package view.laborant;

import manage.DatabaseHandler;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import services.entities.AnalysisRequestServices;

import javax.swing.*;
import java.util.List;
import java.util.Map;

public class AvailableAnalysisTablePanel extends AnalysisTablePanel {

    private AnalysisRequest aR;

    public AvailableAnalysisTablePanel(DatabaseHandler dH, List<Analysis> data, AnalysisRequest aR) {
        super(dH, "Dostupne analize", data);
        this.aR = aR;
    }

    @Override
    protected void initGUI() {
        super.initGUI();
        mainToolbar.add(btnGenerate);

        btnGenerate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greška", JOptionPane.WARNING_MESSAGE);
            } else {
                int id = Integer.parseInt(table.getValueAt(row, 0).toString());
                Measurement m = null;
                Analysis a = null;
                for (Map.Entry<Analysis, Measurement> entry : aR.getAnalysisMeasurementMap().entrySet()) {
                    if (entry.getKey().getId() == id) {
                        m = entry.getValue();
                        a = entry.getKey();
                    }
                }
                if (m == null) {
                    JOptionPane.showMessageDialog(null, "Mjera nije pronađena.", "Greška", JOptionPane.ERROR_MESSAGE);
                } else if ( m.getValue() != 0) {
                    JOptionPane.showMessageDialog(null, "Mjera je već generisana.", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int selection = JOptionPane.showConfirmDialog(null, "Generiši mjeru: ", "Obrada analize", JOptionPane.YES_NO_OPTION);
                    if (selection == JOptionPane.YES_OPTION) {
                        AnalysisRequestServices aRServices = new AnalysisRequestServices(getdH());
                        aRServices.generateMeasurement(m, a);
                        aRServices.checkRequestStatus();
                        getData().remove(a);
                        refresh();
                    }
                }
            }
        });
    }
}
