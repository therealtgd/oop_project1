package modules.entities;

import modules.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Measurement extends Data {

    private LocalDate date;
    private double value;

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

    @Override
    public String toString() {
        return  "vrijednost=" + value + ", datum=" + date;
    }

    @Override
    public String toFileString() {
        return getId() + "," + date + "," + value;
    }
}
