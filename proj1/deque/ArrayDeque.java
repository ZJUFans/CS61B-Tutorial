package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(2 * array.length);
        }
        array[nextFirst] = item;
        nextFirst = getNextFirst();
        size++;
    }

    private int getNextFirst() {
        int res = nextFirst - 1;
        if (res < 0) {
            res += array.length;
        }
        return res;
    }

    private int getFirstIndex() {
        return (nextFirst + 1) % array.length;
    }

    @Override
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(2 * size);
        }
        array[nextLast] = item;
        nextLast = getNextLast();
        size++;
    }

    private int getNextLast() {
        int res = nextLast + 1;
        if (res >= array.length) {
            res -= array.length;
        }
        return res;
    }

    private int getLastIndex() {
        return (nextLast - 1 + array.length) % array.length;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        if (array.length > 16 && ((double) size / array.length) <= 0.25) {
            resize(2 * size);
        }
        int firstIndex = getFirstIndex();
        T res = array[firstIndex];
        array[firstIndex] = null;
        nextFirst = firstIndex;
        size--;
        return res;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        if (array.length > 16 && ((double) size / array.length) <= 0.25) {
            resize(2 * size);
        }
        int lastIndex = getLastIndex();
        T res = array[lastIndex];
        array[lastIndex] = null;
        nextLast = lastIndex;
        size--;
        return res;
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        int start = getFirstIndex();
        for (int i = start; i < size + start; i++) {
            newArray[i - start] = array[i % array.length];
        }
        array = newArray;
        nextFirst = newSize - 1;
        nextLast = size;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public T get(int index) {
        int firstIndex = getFirstIndex();
        return array[(firstIndex + index) % array.length];
    }

    @Override
    public void printDeque() {
        int start = getFirstIndex();
        for (int i = start; i < size + start; i++) {
            System.out.print(array[i % array.length] + " ");
        }
        System.out.println();
    }

    private class MyIterator<T> implements Iterator<T> {
        int index;
        int iterSize;
        MyIterator() {
            index = getFirstIndex();
            iterSize = 0;
        }
        @Override
        public boolean hasNext() {
            return iterSize < size;
        }

        @Override
        public T next() {
            T res = (T) array[index % array.length];
            index++;
            iterSize++;
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
