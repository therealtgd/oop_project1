package modules.entities;

import modules.Data;

import java.util.ArrayList;
import java.util.List;

public class AnalysisRequest extends Data {

    private enum States {
        INITIAL_STATE,
        COLLECTING_SAMPLE,
        PROCESSING,
        FINISHED

    }
    private String patient;

    private States state;
    private List<Analysis> analyses;
    private List<Measurement> measurements;

    public AnalysisRequest(int id) {
        super(id);
        this.state = States.INITIAL_STATE;
    }

    public AnalysisRequest(int id, String patient) {
        super(id);
        this.patient = patient;
        this.state = States.INITIAL_STATE;
        this.analyses = new ArrayList<Analysis>();
        this.measurements = new ArrayList<Measurement>();
    }

    public AnalysisRequest(int id, String patient, String state) {
        super(id);
        this.patient = patient;
        this.state = States.valueOf(state);
        this.analyses = new ArrayList<Analysis>();
        this.measurements = new ArrayList<Measurement>();
    }

    public String getPatient() {
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
        String anaId = null;
        for (Analysis a: analyses) {
            anaId += a.getId() + ";";
        }
        String mesId = null;
        for (Measurement m: measurements) {
            mesId += m.getId();
        }

        return getId() + "," +  patient + "," + state + "," + anaId + "," + mesId;
    }
}
