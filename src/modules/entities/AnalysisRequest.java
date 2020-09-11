package modules.entities;

import modules.Data;

import java.util.List;

public class AnalysisRequest extends Data {

    private enum States {
        INITIAL_STATE,
        COLLECTING_SAMPLE,
        PROCESSING,
        FINISHED;
    }

    private String patient;
    private Enum state;
    private List<Analysis> analyses;

    public AnalysisRequest(int id) {
        super(id);
    }

    public AnalysisRequest(int id, String patient, List<Analysis> analyses) {
        super(id);
        this.patient = patient;
        this.analyses = analyses;
        this.state = States.INITIAL_STATE;
    }

    public String getPatient() {
        return patient;
    }

    public Enum getState() {
        return state;
    }

    public List<Analysis> getAnalyses() {
        return analyses;
    }

    public void setState(States state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ObradaAnalize [" + super.toString() + ", pacjent=" + patient + ", stanje=" + state + ", analize=" + analyses + "]";

    }
}
