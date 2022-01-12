package deque;

import java.util.Comparator;

/**
 * A MaxArrayDeque has all of the methods that an ArrayDeque has,
 * but it also has 2 additional methods and a new constructor.
 * Therefore, I am not going to repeat it, just extends ArrayDeque.
 */
public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> cmp;

    /**
     * Creates a MaxArrayDeque with the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        cmp = c;
    }

    /**
     * Returns the maximum element in the deque as governed by the parameter Comparator c.
     * If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1; i < size(); i++) {
            if (c.compare(get(i), get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return get(maxIndex);
    }
    /**
     *  returns the maximum element in the deque as governed by the previously given Comparator.
     *  If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        return max(cmp);
    }
    
}
