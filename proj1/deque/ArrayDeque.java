package deque;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items = (T[]) new Object[8];
    private int size;
    private int nextFirst;
    private int nextLast;


    /**
     * Creates an empty linked array deque
     */
    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * Minus one circularly.
     */
    public int minusOne(int index) {
        return Math.floorMod(index-1, items.length);

    }
    /**
     * Add one circularly.
     */
    public int plusOne(int index) {
        return Math.floorMod(index+1, items.length);
    }


    /**
     * For arrays of length 16 or more, your usage factor should always be at least 25%.
     * Before performing a remove operation that will bring the number of elements in the array under 25% the length of the array,
     * you should resize the size of the array down.
     * I decided down it to 50%, so the new length will be twice of the size.
     */
    /**  resize an array.
     *
     *   1. For arrays of length 16 or more, usage factor should always be at least 25%
     *      Otherwise halve the array.
     *      For smaller arrays, usage factor can be arbitrarily low.
     *
     *      Usage factor < 25% < 50%. So we can shrink the array size by 2 with no overflow.
     *
     *
     *
     *   2. Array is full and enlarge the Array.
     *
     *      Enlarge Factor = 2
     *
     *
     *   3. My strategy for adjusting capacity of Array:
     *
     *      1) shrinking size
     *
     *      2) increasing size
     *
     *      [current array]: (1 2 3 4 5 6 7 8)
     *      whenever we want to add a new element, we need increase the capacity of
     *      current array, the strategy I applied is as follows
     *
     *      (1 2 3 4 5 6 7 8) -> (null 1 2 3 4 5 6 7 8 null ...)
     *
     *      that means I use `System.arraycopy` function copy all the elements in oringinal
     *      array to a new array with default starting index `1`. And I move the First pointer
     *      to the first position of the new Array (nextFirst = 0;) and Last pointer to the last
     *      element position of the new Array.
     *
     *      Though, it seems a little dummy... :)
     *
     * */
    private void resize() {
        if (size == items.length) {
            resizeHelper(items.length * 2);
        } else if (size < (items.length / 4) && items.length > 16) {
            resizeHelper(items.length / 2);
        }
    }

    /**
     * A helper method of below
     */
    private void resizeHelper(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int startPos = plusOne(nextFirst); // the index of the first item in original deque
        for (int newIndex = 0; newIndex < size; newIndex++) {
            newArray[newIndex] = items[startPos];
            startPos = plusOne(startPos);
        }
        items = newArray;
        nextFirst = capacity - 1; // since the new array is starting from true 0 index.
        nextLast = size;
    }




    /**
     * Adds an item of type T to the front of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, print out a new line.
     *
     * Remember the array I created starts at items[1].
     */
    @Override
    public void printDeque() {
//        for (int i = 1; i < size+1; i++) {
//            System.out.print(items[i] + " ");
//        }

//        Arrays.stream(items).filter(Objects::nonNull).map(p -> p + " ").forEach(System.out::print);

        Arrays.stream(items).map(p -> p + " ").forEach(System.out::print);
        System.out.println();

    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        resize();
        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        resize();
        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        size --;
        return item;
    }



    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     * index 0 1 2 3 4 5 6 7
     * size  1 2 3 4 5 6 7 8
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        index = Math.floorMod(plusOne(nextFirst) + index, items.length);
        return items[index];
    }

    /**
     * The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int index;

        ArrayDequeIterator() {
            index = 0;
        }

        public boolean hasNext() {
            return index < size;
        }

        public T next() {
            T item = get(index);
            index = plusOne(index);
            return item;
        }
    }

    /**
     * Returns whether or not the parameter o is equal to the Deque.
     * o is considered equal if it is a Deque and if it contains the same contents
     * (as goverened by the generic T’s equals method) in the same order.
     * (ADDED 2/12: You’ll need to use the instance of keywords for this.)
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }

        Deque other = (Deque) o;
        if (size != other.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!(get(i).equals(other.get(i)))) {
                return false;
            }
        }
        return true;
    }

}