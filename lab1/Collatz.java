/** Class that prints the Collatz sequence starting from a given number.
 *  @author Fiora
 *  @date 2021.10.21
 */
public class Collatz {

    /** If n is even, the next number is n/2.
     * If n is odd, the next number is 3n + 1.
     * If n is 1, the sequence is over.
     * Since all the inputs are int, only when n equals to 2 the sequence will go to 1.
     * Therefore, I put this assumption at the first place.*/
    public static int nextNumber(int n) {
        if (n == 2) {
            return 1;
        } else if (n % 2 == 0) {
            return n / 2;
        } else {
            return 3*n + 1;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.print(n + " ");
        while (n != 1) {
            n = nextNumber(n);
            System.out.print(n + " ");
        }
        System.out.println();
    }
}

