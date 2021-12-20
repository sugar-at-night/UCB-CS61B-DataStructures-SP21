package deque;

public class LinkedListDeque {

    private int size;
    private final StuffNode sentinel;
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
     * Adds an item to the front of the list.
     */
    public void addFirst(T item) {
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /**
     * Adds an item to the end of the list.
     */
    public void addLast(T item) {
        sentinel.prev = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**
     * Return if this list is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return the size.
     */
    public int size() {
        return size;
    }

    /**
     * Print the deque.
     */
    public void printDeque() {
        StuffNode p = sentinel.next;
        for (int i = 0; i < size - 1; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.print(p.item);
    }

    /**
     * Remove the first item in the list if the list is not null.
     */
    public T removeFirst() {
        if (sentinel.next == sentinel)
            return null;
        StuffNode temp = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return temp.item;
    }

    /**
     * Remove the last item in the list if the list is not null.
     */
    public T removeLast() {
        if (sentinel.prev == sentinel)
            return null;
        StuffNode temp = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return temp.item;
    }

    /**
     * Return the item according to the index.
     */
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
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class StuffNode {
        public StuffNode prev;
        public T item;
        public StuffNode next;

        public StuffNode(StuffNode p, T i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private StuffNode<T> p;

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


}
