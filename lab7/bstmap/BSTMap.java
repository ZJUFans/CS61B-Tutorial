package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {
    int size = 0;
    BSTNode root;
    private class BSTNode {
        K key;
        V val;
        BSTNode left;
        BSTNode right;

        BSTNode (K k, V v) {
            key = k;
            val = v;
        }
        BSTNode (K k, V v, BSTNode l, BSTNode r) {
            key = k;
            val = v;
            left = l;
            right = r;
        }

        BSTNode get(K k) {
            if (k != null && key.equals(k)) {
                return this;
            }
            if (left != null && key.compareTo(k) > 0) {
                return left.get(k);
            }
            if (right != null && key.compareTo(k) < 0) {
                return right.get(k);
            }

            return null;
        }
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }
        return root.get(key) != null;
    }

    @Override
    public V get(K key) {
        if (root == null) {
            return null;
        }
        BSTNode node = root.get(key);
        return node.val;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new BSTNode(key, value);
            size = 1;
            return;
        }
        BSTNode node = root;
        while (node != null) {
            if (node.key.compareTo(key) > 0) {
                if (node.left == null) {
                    node.left = new BSTNode(key, value);
                    break;
                }
                node = node.left;
            } else if (node.key.compareTo(key) < 0) {
                if (node.right == null) {
                    node.right = new BSTNode(key, value);
                    break;
                }
                node = node.right;
            } else {
                node.val = value;
                return;
            }
        }
        size += 1;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
