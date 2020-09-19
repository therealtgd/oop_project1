package services.utils;

import modules.utils.Range;

import java.util.concurrent.ThreadLocalRandom;

public class MeasurementGenerator {

    public static double generate(Range refValue) {
        return ThreadLocalRandom.current().nextDouble(refValue.getMin() - (refValue.getMin() * 0.05),
                refValue.getMax() + 1 + (refValue.getMax() * 0.05));
    }

}
