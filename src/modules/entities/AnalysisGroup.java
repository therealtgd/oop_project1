package modules.entities;

import modules.Data;

import java.util.ArrayList;
import java.util.List;

public class AnalysisGroup extends Data {

    private String type;
    private List<Analysis> analyses;

    public AnalysisGroup(int id) {
        super(id);
    }

    public AnalysisGroup(int id, String type) {
        super(id);
        this.type = type;
        this.analyses = new ArrayList<Analysis>();
    }

    public String getType() {
        return type;
    }

    public List<Analysis> getAnalyses() {
        return analyses;
    }

    public void setAnalyses(List<Analysis> analyses) {
        this.analyses = analyses;
    }

    public void addAnalyses(Analysis a) {
        this.analyses.add(a);
    }

    @Override
    public String toString() {
        return "GrupaAnaliza [" + super.toString() + ", tip=" + type + ", analize=" + analyses + "]";

    }

    @Override
    public String toFileString() {
        String retVal = getId() + type;
        for (Analysis a : analyses){
            retVal += a.toFileString() + ";";
        }
        return retVal;
    }
}
