package modules.entities;

import modules.Data;

import java.util.List;

public class Specialization {

    private List<Analysis> specs;


    public Specialization(List<Analysis> specs) {
        this.specs = specs;
    }

    public List<Analysis> getSpecs() {
        return specs;
    }

    public void addSpec(Analysis a) {
        this.specs.add(a);
    }

    @Override
    public String toString() {
        return "Specijalizacija [" + specs + "]";
    }

}
