package services.entities;

import manage.DatabaseHandler;
import modules.DTO.AnalysisRequestDTO;
import modules.entities.Analysis;
import modules.entities.AnalysisRequest;
import modules.entities.Measurement;
import services.Services;
import services.utils.Builder;
import services.utils.MeasurementGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AnalysisRequestServices extends Services {

    public AnalysisRequestServices(DatabaseHandler dH) {
        super(dH);
    }

    public String printAnalysisRequest(AnalysisRequest aR) {
        return aR.toString();
    }

    public void requestAnalysis(AnalysisRequestDTO aRDTO) {
        AnalysisRequest aR = new Builder(getdH()).buildAnalysiRequest(aRDTO);
        aR.setId(getdH().getEntityDatabase().getAnalysisRequestNotificationDatabase().getMaxId() + 1);
        getdH().getEntityDatabase().getAnalysisRequestDatabase().addData(aR);
        new NotificationServices(getdH()).sendAnalysisRequestNotification(aR);
    }

    public void generateMeasurement(Measurement m, Analysis a) {
        m.setDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
        m.setValue(MeasurementGenerator.generate(a.getReferenceValue()));
        getdH().getEntityDatabase().getAnalysisRequestDatabase().saveData();
        getdH().getEntityDatabase().getMeasurementDatabase().saveData();
        getdH().getEntityDatabase().getAnalysisRequestDatabase().saveData();
    }

    public void checkRequestStatus() {
        for (AnalysisRequest aR : getdH().getEntityDatabase().getAnalysisRequestDatabase().getData()) {
            boolean delete = true;
            for (Map.Entry<Analysis, Measurement> entry : aR.getAnalysisMeasurementMap().entrySet()) {
                if (entry.getValue().getValue() == 0.0) {
                    delete = false;
                    break;
                }
            }
            if (delete) {
                aR.setState("FINISHED");
                getdH().getEntityDatabase().getAnalysisRequestDatabase().saveData();
            }
        }
    }
}
