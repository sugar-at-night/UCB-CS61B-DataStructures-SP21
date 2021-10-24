/** @author Fiora
 */
public class TriangleDrawer {

    public static void drawTriangle(int N) {
        for (int row = 1; row <= N; row++) {
            for (int n_star = 1; n_star <= row; n_star++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        drawTriangle(10) ;
    }
}
