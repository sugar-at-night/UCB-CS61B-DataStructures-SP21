package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;         // root of BST

    private class Node {
        private K key;           // sorted by key
        private V value;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree
                                   // The root of any subtree is also counted in the subtree
        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

    }

    // Initializes an empty BSTMap.
    public BSTMap() {
    }


    /**
     * Removes all of the mappings from this map.
     */
    @Override
    public void clear() {
        root = null;
    }


    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    private boolean containsKey(Node node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return containsKey(node.left, key);
        } else if (cmp > 0) {
            return containsKey(node.right, key);
        }
        return true;
    }


    /**
     * Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        }
        return node.value;
    }


    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        return x == null ? 0 : x.size;
    }


    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value,1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     *  Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<>();
        addKeys(root, set);
        return set;
    }

    private void addKeys(Node node, Set<K> set) {
        if (node == null) {
            return;
        }
        addKeys(node.left, set);
        set.add(node.key);
        addKeys(node.right, set);
    }


    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        V removeValue = get(key);
        root = remove(root, key);
        return removeValue;
    }


    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    private Node remove(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            // when the Node x only has one child
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            // x has 2 children
            Node t = x;
            x = min(t.right);
            x.right = removeMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V val) {
        if (key == null) {
            return null;
        }
        if (get(key) != val) {
            return null;
        }
        V removeValue = get(key);
        root = remove(root, key);
        return removeValue;
    }



    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (node == null) {
            return;
        }
        printInOrder(node.left);
        System.out.println(node.key.toString() + " -> " + node.value.toString());
        printInOrder(node.right);
    }

}