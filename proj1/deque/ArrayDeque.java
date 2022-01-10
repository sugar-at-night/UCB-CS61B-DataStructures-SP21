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
     * Starts at items[1]
     */
    public ArrayDeque(T item) {
        items[1] = item;
        size = 1;
        nextFirst = 0;
        nextLast = 2;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addFirst(T item) {
        items[nextFirst] = item;
        size += 1;
        nextFirst -= 1;
        if (nextFirst == -1) {
            resize(size * 2);
        }
    }

    /**
     * Adds an item of type T to the back of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addLast(T item) {
        items[nextLast] = item;
        size += 1;
        nextLast += 1;
        if (nextLast == items.length) {
            resize(size * 2);
        }
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
        for (int i = 1; i < size+1; i++) {
            System.out.print(items[i] + " ");
        }
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
        nextFirst += 1;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        shrinkSize();
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
        nextLast -= 1;
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        shrinkSize();
        return item;
    }


    /**
     * For arrays of length 16 or more, your usage factor should always be at least 25%.
     * Before performing a remove operation that will bring the number of elements in the array under 25% the length of the array,
     * you should resize the size of the array down.
     * I decided down it to 50%, so the new length will be twice of the size.
     */
    private void shrinkSize() {
        if (isEmpty()) {
            resize(8);
        } else if (size/ items.length < 0.25 && size * 2 >= 8) {
            resize(size * 2);
        }
    }

    /**
     * A helper method of below
     */
    private void resize(int l) {
        T[] newItems = (T[]) new Object[l];
        int firstPos = Math.abs(l - size) / 2;
        System.arraycopy(items, nextFirst + 1, newItems, firstPos, size);
        items = newItems;
        nextFirst = firstPos - 1;
        nextLast = firstPos + size;
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
        if (index < 0 || index > size - 1) {
            return null;
        }
        int itemIndex = nextFirst + 1 + index;
        return items[itemIndex];
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
            index += 1;
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