package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.assertEquals;


public class MaxArrayDequeTest {

    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer i1, Integer i2) {
            return i1 - i2;
        }
    }

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String str1, String str2) {
            int l1 = str1.length();
            int l2 = str2.length();
            int lmin = Math.min(l1, l2);

            for (int i = 0; i < lmin; i++) {
                int str1_ch = (int) str1.charAt(i);
                int str2_ch = (int) str2.charAt(i);

                if (str1_ch != str2_ch) {
                    return str1_ch - str2_ch;
                }
            }

            // Edge case for strings like
            // String 1="Geeks" and String 2="Geeksforgeeks"
            if (l1 != l2) {
                return l1 - l2;
            }

            // If none of the above conditions is true,
            // it implies both the strings are equal
            else {
                return 0;
            }

        }
    }

    private static class StringLengthComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }


    @Test
    public void maxWithoutComparatorTest() {
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(new IntComparator());

        for (int i = 0; i < 5; i++) {
            mad.addLast(i);
        }

        assertEquals((Integer) 4, mad.max());
    }

    @Test
    public void maxWithComparatorTest() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringComparator());

        mad.addLast("Java is goody good!");
        mad.addLast("java is good");

        assertEquals("java is good", mad.max());
        assertEquals("Java is goody good!", mad.max(new StringLengthComparator()));
    }

    @Test
    public void maxWithComparatorTest2() {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new StringLengthComparator());

        mad.addLast("A");
        mad.addLast("a");

        assertEquals("A", mad.max());
    }

}