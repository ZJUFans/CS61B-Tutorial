package deque;

public class LinkedListDeque<T> {
    private int size;
    private TNode sentinel;

    public class TNode<T> {
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

    public T removeFirst() {
        TNode<T> removed = sentinel.next;
        if(removed == null) return null;
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

    public T removeLast() {
        TNode<T> removed = sentinel.prev;
        if(removed == null) return null;
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

    public T get(int index) {
        TNode<T> cur = sentinel;
        while (index > 0) {
            cur = cur.next;
            if (cur == sentinel) return null;
        }
        return cur.next.value;
    }

    public T getRecursive(int index) {
        return (T) getRecursiveHelper(index, sentinel.next);
    }

    public T getRecursiveHelper(int index, TNode<T> node) {
        if (index == 0) return node.value;
        if (node == sentinel) return null;
        return getRecursiveHelper(index - 1, node.next);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        TNode cur = sentinel.next;
        while (cur != sentinel) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println();
    }

}
