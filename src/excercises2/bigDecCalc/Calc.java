/**
 *
 *  @author Klekot Oskar S30096
 *
 */

package excercises2.bigDecCalc;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static java.math.RoundingMode.HALF_UP;

public class Calc {

    private final Map<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> operations;

    public Calc() {
        this.operations = new HashMap<>();
        operations.put("+", BigDecimal::add);
        operations.put("-", BigDecimal::subtract);
        operations.put("*", BigDecimal::multiply);
        operations.put("/", (a, b) -> a.divide(b, 7, HALF_UP));

    }

    public String doCalc(String expression) {

        String[] parts = expression.split("\\s+");
        String result;
        try {
            BigDecimal a = new BigDecimal(parts[0]);
            String operation = parts[1];
            BigDecimal b = new BigDecimal(parts[2]);
            result = operations.get(operation).apply(a, b).stripTrailingZeros().toPlainString();
        } catch (Exception e) {
            return "Invalid command to calc";
        }
        return result;
    }
}