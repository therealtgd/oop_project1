package modules.entities;

import modules.Data;
import modules.utils.Range;


public class Analysis extends Data {

    private String analysisGroup;
    private String type;
    private Range referenceValue;
    private String unit;
    private double cost;

    public Analysis(int id, String analysisGroup, String type, Range referenceValue, String unit, double cost) {
        super(id);
        this.analysisGroup = analysisGroup;
        this.type = type;
        this.referenceValue = referenceValue;
        this.unit = unit;
        this.cost = cost;
    }

    public String getAnalysisGroup() {
        return analysisGroup;
    }

    public String getType() {
        return type;
    }

    public Range getReferenceValue() {
        return referenceValue;
    }

    public String getUnit() {
        return unit;
    }

    public double getCost() {
        return cost;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Analiza[id=" + super.toString() + ", grupa=" + analysisGroup + ", tip=" + type + ", cena=" + cost + "]";
    }

    public String toFileString() {
        return getId() + "," + type + "," + referenceValue.toFileString() + "," + unit + "," + cost;
    }
}
