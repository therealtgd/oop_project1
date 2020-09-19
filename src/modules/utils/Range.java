package modules.utils;

import modules.Data;

public class Range implements Comparable<Range>{

    private double max;
    private double min;

    public Range(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public boolean contains(double value) {
        return (value >= min && value <= max);
    }

    public static Range parseRange(String range) {
        String[] tokens = range.split("-");
        return new Range(Double.parseDouble(tokens[0]), Double.parseDouble(tokens[1]));
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    @Override
    public String toString() {
        return "Opseg [" + min + "-" + max + "]";
    }

    public String toFileString() {
        return min + "-" + max;
    }

    @Override
    public int compareTo(Range o) {
        return Double.compare(min, o.min) + Double.compare(max, o.max);
    }
}
