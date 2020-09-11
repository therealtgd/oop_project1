package modules.entities;

import java.util.Date;

public class Measurement {

    private Date date;
    private double value;
    private Analysis analysis;


    public Measurement() {
    }

    public Measurement(Date date, double value, Analysis analysis) {
        this.date = date;
        this.value = value;
        this.analysis = analysis;
    }

    public Date getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    @Override
    public String toString() {
        return "analiza=" + analysis + ", vrijednost=" + value +"datum=" + date;
    }
}
