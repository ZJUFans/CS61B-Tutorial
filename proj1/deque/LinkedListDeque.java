package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private TNode sentinel;

    private class TNode<T> {
        T value;
        TNode<T> prev;
        TNode<T> next;

        public TNode() {

        }

        public TNode(T value) {
            this.value = value;
        }
    }

    public LinkedListDeque() {
        sentinel = new TNode<>();
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        TNode<T> node = new TNode<>(item);
        TNode<T> ori = sentinel.next;
        if (ori == null) {
            sentinel.next = node;
            sentinel.prev = node;
            node.prev = sentinel;
            node.next = sentinel;
        } else {
            sentinel.next = node;
            node.next = ori;
            ori.prev = node;
            node.prev = sentinel;
        }
    }

    @Override
    public void addLast(T item) {
        size += 1;
        TNode<T> node = new TNode<>(item);
        TNode<T> ori = sentinel.prev;
        if (ori == null) {
            sentinel.next = node;
            sentinel.prev = node;
            node.prev = sentinel;
            node.next = sentinel;
        } else {
            sentinel.prev = node;
            node.prev = ori;
            ori.next = node;
            node.next = sentinel;
        }
    }

    @Override
    public T removeFirst() {
        TNode<T> removed = sentinel.next;
        if (removed == null) {
            return null;
        }
        size -= 1;
        TNode<T> node = removed.next;
        if (node == sentinel) {
            sentinel.next = null;
            sentinel.prev = null;
            return removed.value;
        }
        sentinel.next = node;
        node.prev = sentinel;
        removed.prev = null;
        removed.next = null;
        return removed.value;
    }

    @Override
    public T removeLast() {
        TNode<T> removed = sentinel.prev;
        if (removed == null) {
            return null;
        }
        size -= 1;
        TNode<T> node = removed.prev;
        if (node == sentinel) {
            sentinel.next = null;
            sentinel.prev = null;
            return removed.value;
        }
        sentinel.prev = node;
        node.next = sentinel;
        removed.prev = null;
        removed.next = null;
        return removed.value;
    }

    @Override
    public T get(int index) {
        TNode<T> cur = sentinel;
        while (index > 0) {
            cur = cur.next;
            if (cur == sentinel) {
                return null;
            }
            index--;
        }
        return cur.next.value;
    }

    public T getRecursive(int index) {
        return (T) getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, TNode<T> node) {
        if (index == 0) {
            return node.value;
        }
        if (node == sentinel) {
            return null;
        }
        return getRecursiveHelper(index - 1, node.next);
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public void printDeque() {
        TNode cur = sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    private class MyIterator<T> implements Iterator<T> {
        private TNode cur;
        private int iterSize;
        MyIterator() {
            cur = sentinel.next;
            iterSize = 0;
        }
        @Override
        public boolean hasNext() {
            if (iterSize >= size()) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            T res = (T) cur.value;
            cur = cur.next;
            iterSize += 1;
            return res;
        }
    }
    public Iterator<T> iterator() {
        return new MyIterator<>();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }

        if (o instanceof Deque<?>) {
            Deque<T> q = (Deque<T>) o;
            if (this.size() != q.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (!get(i).equals(q.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
