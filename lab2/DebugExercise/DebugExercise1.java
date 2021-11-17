package DebugExercise;

/**
 * Exercise for learning how the debug, breakpoint, and step-into
 * feature work.
 */

/**
 * I changed all the variables to double rather than int
 * and feature works now.
 * It's tricky here, int/int=int.
 * In integer division, if the answer is not a perfect integer,
 * the digits after the decimal point will be removed.
 * To change type of result,
 * need to change type of at least one of arguments
 * to floating point like float or double
 */

public class DebugExercise1 {
    public static double divideThenRound(double top, double bottom) {
        double quotient = top / bottom;
        double result = Math.round(quotient);
        return result;
    }

    public static void main(String[] args) {
        int t = 10;
        int b = 2;
        double result = divideThenRound(t, b);
        System.out.println("round(" + t + "/" + b + ")=" + result);

        int t2 = 9;
        int b2 = 4;
        double result2 = divideThenRound(t2, b2);
        System.out.println("round(" + t2 + "/" + b2 + ")=" + result2);

        int t3 = 3;
        int b3 = 4;
        double result3 = divideThenRound(t3, b3);
        System.out.println("round(" + t3 + "/" + b3 + ")=" + result3);
    }
}
