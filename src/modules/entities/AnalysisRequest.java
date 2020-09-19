package modules.entities;

import modules.Data;
import modules.users.Patient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisRequest extends Data {

    private enum States {
        INITIAL_STATE,
        COLLECTING_SAMPLE,
        PROCESSING,
        FINISHED

    }

    private Patient patient;
    private States state;
    private Map<Analysis, Measurement> analysisMeasurementMap;
    private boolean homeVisit;

    public AnalysisRequest(Patient patient, Map<Analysis, Measurement> analysisMeasurementMap, boolean homeVisit) {
        this.patient = patient;
        this.analysisMeasurementMap = analysisMeasurementMap;
        this.homeVisit = homeVisit;
        this.state = States.INITIAL_STATE;
    }

    public AnalysisRequest(int id, Patient patient, String state) {
        super(id);
        this.patient = patient;
        this.state = States.valueOf(state);
        this.analysisMeasurementMap = new HashMap<>();
    }

    public Patient getPatient() {
        return patient;
    }

    public States getState() {
        return state;
    }

    public void setState(String state) {
        this.state = States.valueOf(state);
    }

    public boolean isHomeVisit() {
        return homeVisit;
    }

    public States[] getStates() {
        return States.values();
    }

    public Map<Analysis, Measurement> getAnalysisMeasurementMap() {
        return analysisMeasurementMap;
    }

    public void setAnalysisMeasurementMap(Map<Analysis, Measurement> analysisMeasurementMap) {
        this.analysisMeasurementMap = analysisMeasurementMap;
    }

    @Override
    public String toString() {
        return "ZahtjevZaAnalizu [" + super.toString() + ", pacjent=" + patient + ", stanje=" + state + ", analize=" + analysisMeasurementMap.keySet() + "]";

    }

    @Override
    public String toFileString() {
        String pId = String.valueOf(getPatient().getId());
        String anaId = "";
        for (Analysis a : analysisMeasurementMap.keySet()) {
            anaId += a.getId() + ";";
        }
        String mId = "";
        for (Measurement m : analysisMeasurementMap.values()) {
            mId += m.getId() + ";";
        }

        return getId() + "," + pId + "," + state + "," + anaId + "," + mId;
    }
}
