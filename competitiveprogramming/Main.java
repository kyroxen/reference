package competitiveprogramming;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        System.out.println(add(BigDecimal.ONE, BigDecimal.TEN));

    }
    private static BigDecimal add(BigDecimal ... operands) {

        BigDecimal summation = BigDecimal.ZERO;
        for (int i = 0 ; i < operands.length ; i++) {
            if(operands[i] == null)
                operands[i] = BigDecimal.ZERO;
            summation = summation.add(operands[i]);
        }
        return summation;
    }
}
