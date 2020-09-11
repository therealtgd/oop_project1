package modules.entities;

import modules.utils.Range;

public class Analysis {

    private String type;
    private Range referenceValue;
    private String unit;
    private double cost;

    public Analysis(String type, Range referenceValue, String unit, double cost) {
        this.type = type;
        this.referenceValue = referenceValue;
        this.unit = unit;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Analiza[id=" + super.toString() + ", tip=" + type + ", cena=" + cost + "]";
    }

    public String toFileString() {
        return type + ";" +referenceValue.toFileString() + ";" + unit + ";" + cost;
    }

    public static Analysis parseAnalysis(String aData) {
        String[] tokens = aData.split(";");

        return null;
    }

}
