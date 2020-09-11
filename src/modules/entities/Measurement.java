package modules.entities;

import modules.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Measurement extends Data {

    private LocalDate date;
    private double value;
    private Analysis analysis;


    public Measurement(int id) {
        super(id);
    }

    public Measurement(int id, String date, double value) {
        super(id);
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy."));
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public Analysis getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Analysis analysis) {
        this.analysis = analysis;
    }

    @Override
    public String toString() {
        return "analiza=" + analysis + ", vrijednost=" + value + analysis.getUnit() + ", datum=" + date;
    }

    @Override
    public String toFileString() {
        return getId() + "," + date + "," + value + "," + analysis.getId();
    }
}
