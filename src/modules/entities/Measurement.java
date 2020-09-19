package modules.entities;

import modules.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class Measurement extends Data {

    private int analysisId;
    private LocalDate date;
    private double value;

    public Measurement(int id) {
        super(id);
    }

    public Measurement(int id, int analysisId) {
        super(id);
        this.analysisId = analysisId;
    }

    public Measurement(int id, int analysisId, double value) {
        super(id);
        this.analysisId = analysisId;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy."));;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getAnalysisId() {
        return analysisId;
    }

    @Override
    public String toString() {
        return  "vrijednost=" + value + ", datum=" + date;
    }

    @Override
    public String toFileString() {
        String dString = "null";
        if (date != null) {
            dString = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy."));
        }
        return getId() + "," + analysisId + "," + dString + "," + value;
    }
}
