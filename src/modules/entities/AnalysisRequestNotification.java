package modules.entities;

import modules.users.Patient;

public class AnalysisRequestNotification extends MyNotification {

    private Patient patient;
    private boolean homeVisit;
    private AnalysisRequest analysisRequest;

    public AnalysisRequestNotification(String title, Patient patient, boolean homeVisit, AnalysisRequest analysisRequest) {
        super(title);
        this.patient = patient;
        this.homeVisit = homeVisit;
        this.analysisRequest = analysisRequest;
    }
}
