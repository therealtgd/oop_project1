package modules.entities;

import modules.Data;
import modules.utils.Range;

import java.util.HashMap;
import java.util.Map;


public class Analysis extends Data {

    private String analysisGroup;
    private String type;
    private Range referenceValue;
    private String unit;
    private double cost;

    public Analysis(int id, String type, Range referenceValue, String unit, double cost) {
        super(id);
        this.type = type;
        this.referenceValue = referenceValue;
        this.unit = unit;
        this.cost = cost;
        this.analysisGroup = "";
    }

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

    public void setAnalysisGroup(String analysisGroup) {
        this.analysisGroup = analysisGroup;
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

    public Map<String, Object> getParameters() {
        Map<String, Object> retVal = new HashMap<>();
        retVal.put("Grupa analiza:", analysisGroup);
        retVal.put("Tip:", type);
        retVal.put("Referentna vrednost:", referenceValue);
        retVal.put("Merna jedinica:", unit);
        retVal.put("Cena:", cost);
        return retVal;
    }

    @Override
    public String toString() {
        return "Analiza[id=" + super.toString() + ", grupa=" + analysisGroup + ", tip=" + type + ", cena=" + cost + "]";
    }

    public String toFileString() {
        return getId() + "," + analysisGroup + "," + type + "," + referenceValue.toFileString() + "," + unit + "," + cost + ",";
    }
}
