package modules.entities;

import modules.Data;

import java.util.List;

public class Specialization {

    private List<AnalysisGroup> specs;


    public Specialization(List<AnalysisGroup> specs) {
        this.specs = specs;
    }

    public List<AnalysisGroup> getSpecs() {
        return specs;
    }

    public void addSpec(AnalysisGroup aG) {
        this.specs.add(aG);
    }

    @Override
    public String toString() {
        return "Specijalizacija [" + specs + "]";
    }

}
