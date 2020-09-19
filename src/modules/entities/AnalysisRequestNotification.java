package modules.entities;

import java.time.LocalDateTime;

import java.util.Map;

public class AnalysisRequestNotification extends MyNotification {

    private String patient;
    private boolean homeVisit;
    private AnalysisRequest analysisRequest;

    public AnalysisRequestNotification(String patient, boolean homeVisit, AnalysisRequest analysisRequest) {
        super("Zahtjev za analizu");
        this.patient = patient;
        this.homeVisit = homeVisit;
        this.analysisRequest = analysisRequest;
    }

    public AnalysisRequestNotification(int id, String title, LocalDateTime dateTime, String state, String patient, boolean homeVisit, AnalysisRequest analysisRequest) {
        super(id, title, dateTime, state);
        this.patient = patient;
        this.homeVisit = homeVisit;
        this.analysisRequest = analysisRequest;
    }

    public String getPatient() {
        return patient;
    }

    public boolean isHomeVisit() {
        return homeVisit;
    }

    public AnalysisRequest getAnalysisRequest() {
        return analysisRequest;
    }

    public void setHomeVisit(boolean homeVisit) {
        this.homeVisit = homeVisit;
    }

    public void setAnalysisRequest(AnalysisRequest analysisRequest) {
        this.analysisRequest = analysisRequest;
    }

    @Override
    public Map<String, Object> getParameters() {
        Map<String, Object> retVal = super.getParameters();
        retVal.put("Pacjent:", patient);
        retVal.put("Kućna posjeta:", homeVisit);
        retVal.put("Zahtjev za analizu:", analysisRequest);
        return retVal;
    }

    @Override
    public String toString() {
        return "ZahtjevAnalizeNotifikacija [" + super.toString() + "pacjent=" + patient + ", kućna posjeta=" + homeVisit + ", Zahtjev za analizu=" + analysisRequest.getId() + ']';
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + patient + "," + homeVisit + "," + analysisRequest.getId();
    }
}
