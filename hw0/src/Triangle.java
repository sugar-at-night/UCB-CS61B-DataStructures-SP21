/** @author Fiora
 */
public class Triangle {
    public static void main(String[] args) {
        for (int row = 1; row <= 5; row++) {
            for (int n_star = 1; n_star <= row; n_star++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
