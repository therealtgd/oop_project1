package modules.entities;

import modules.Data;
import modules.users.Patient;

import java.util.ArrayList;
import java.util.List;

public class AnalysisRequest extends Data {

    private enum States {
        INITIAL_STATE,
        COLLECTING_SAMPLE,
        PROCESSING,
        FINISHED

    }
    private Patient patient;
    private States state;
    private List<Analysis> analyses;
    private List<Measurement> measurements;
    private boolean homeVisit;

    public AnalysisRequest(Patient patient, List<Analysis> analyses, boolean homeVisit) {
        this.patient = patient;
        this.analyses = analyses;
        this.homeVisit = homeVisit;
        this.measurements = new ArrayList<>();
        this.state = States.INITIAL_STATE;
    }

    public AnalysisRequest(int id, Patient patient, String state) {
        super(id);
        this.patient = patient;
        this.state = States.valueOf(state);
        this.analyses = new ArrayList<>();
        this.measurements = new ArrayList<>();
    }

    public Patient getPatient() {
        return patient;
    }

    public States getState() {
        return state;
    }

    public List<Analysis> getAnalyses() {
        return analyses;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setState(States state) {
        this.state = state;
    }

    public boolean isHomeVisit() {
        return homeVisit;
    }

    public void addAnalysis(Analysis a) {
        analyses.add(a);
    }

    public void addMeasurement(Measurement m) {
        measurements.add(m);
    }


    @Override
    public String toString() {
        return "ObradaAnalize [" + super.toString() + ", pacjent=" + patient + ", stanje=" + state + ", analize=" + analyses + "]";

    }

    @Override
    public String toFileString() {
        String pId = String.valueOf(getPatient().getId());
        String anaId = "";
        for (Analysis a: analyses) {
            anaId += a.getId() + ";";
        }
        String mesId = "";
        for (Measurement m: measurements) {
            mesId += m.getId() + ";";
        }

        return getId() + "," +  pId + "," + state + "," + anaId + "," + mesId;
    }
}
