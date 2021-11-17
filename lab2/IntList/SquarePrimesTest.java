package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testSquarePrimesSimpleTwo() {
        IntList lst = IntList.of(17, 1, 0, 17, 18, 2, -2);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("289 -> 1 -> 0 -> 289 -> 18 -> 4 -> -2", lst.toString());
        assertTrue(changed);

    }

    @Test
    public void testSquarePrimesSimpleThree() {
        IntList lst = IntList.of(1, 0, 18, -2);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("1 -> 0 -> 18 -> -2", lst.toString());
        assertFalse(changed);

    }

    @Test
    public void testSquarePrimesSimpleFour() {
        IntList lst = IntList.of(17, 17, 1, 0);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("289 -> 289 -> 1 -> 0", lst.toString());
        assertTrue(changed);

    }
}
