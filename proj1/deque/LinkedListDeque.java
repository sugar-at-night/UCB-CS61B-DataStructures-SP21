package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    private class StuffNode {
        public StuffNode prev;
        public T item;
        public StuffNode next;
        /**
         * Creates a constructor of StuffNode.
         */
        public StuffNode(StuffNode p, T i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private int size;
    private StuffNode sentinel;

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addFirst(T item) {
        StuffNode NewNode = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.prev = NewNode;
        sentinel.next = NewNode;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     * You can assume that item is never null.
     */
    @Override
    public void addLast(T item) {
        StuffNode NewNode = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.next = NewNode;
        sentinel.prev = NewNode;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    /**
     * No need now, since there is a default one.
     */
//    public boolean isEmpty() {
//        return size == 0;
//    }

    /**
     * Returns the number of items in the deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     *  Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
    @Override
    public void printDeque() {
        StuffNode p = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print(p.item);
        System.out.println();

    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel)
            return null;
        StuffNode temp = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return temp.item;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel)
            return null;
        StuffNode temp = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return temp.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null.
     * Must not alter the deque!
     */
    @Override
    public T get(int index) {
        StuffNode p = sentinel.next;
        if (index >= size || p == sentinel) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(StuffNode p, int index) {
        if (p == sentinel) {
            return null;
        }
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }

    /**
     * The Deque objects we’ll make are iterable (i.e. Iterable<T>)
     * so we must provide this method to return an iterator.
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private StuffNode p;

        public LinkedListDequeIterator() {
            p = sentinel.next;
        }

        public boolean hasNext() {
            return (p != sentinel);
        }

        public T next() {
            T item = p.item;
            p = p.next;
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

        StuffNode p = sentinel.next;
        for (int i = 0; i < size; i++) {
            if (!p.item.equals(other.get(i))) {
                return false;
            }
            p = p.next;
        }
        return true;
    }


}
