/** @author Fiora
 */
public class MaxValue {
    public static int maxFOR(int[] m) {
        int maxNum = 0;
        for (int i = 0; i < m.length; i = i + 1) {
            if (m[i] >= maxNum) {
                maxNum = m[i] ;
            }
        }
        return maxNum;
    }

    public static int maxWHILE(int[] m) {
        int i = 0;
        int maxNum = 0;
        while (i < m.length) {
            if (m[i] >= maxNum) {
                maxNum = m[i];
            }
            i = i + 1;
        }
        return maxNum;
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println(maxFOR(numbers));
        System.out.println(maxWHILE(numbers));
    }
}
