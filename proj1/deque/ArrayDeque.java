package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] array1;
    private T[] array2;
    private int start1;
    private int size1;
    private int start2;
    private int size2;
    public T[] getArray1() {
        return array1;
    }

    public T[] getArray2() {
        return array2;
    }

    public int getStart1() {
        return start1;
    }

    public int getSize1() {
        return size1;
    }

    public int getStart2() {
        return start2;
    }

    public int getSize2() {
        return size2;
    }


    public ArrayDeque() {
        array1 = (T[]) new Object[8];
        array2 = (T[]) new Object[8];
        start1 = 0;
        size1 = 0;
        start2 = 0;
        size2 = 0;
    }

    @Override
    public void addFirst(T item) {
        if (start1 + size1 >= array1.length - 1) {
            resize(array1, Math.max(size1 * 2, size1 + 8));
        }
        array1[start1 + size1] = item;
        size1 += 1;
    }

    @Override
    public void addLast(T item) {
        if (start2 + size2 >= array2.length - 1) {
            resize(array2, Math.max(size2 * 2, size2 + 8));
        }
        array2[start2 + size2] = item;
        size2 += 1;
    }

    @Override
    public T removeFirst() {
        if (array1.length >= 16 && (size1 / array1.length) <= 0.25) {
            resize(array1, 2 * size1);
        }
        if (size1 > 0) {
            T res = array1[start1 + size1 - 1];
            array1[start1 + size1 - 1] = null;
            size1 -= 1;
            return res;
        } else if (size2 > 0) {
            T res = array2[start2];
            size2 -= 1;
            start2 += 1;
            return res;
        } else {
            return null;
        }
    }

    @Override
    public T removeLast() {
//        if (array2.length >= 16 && (size2 / array2.length) <= 0.25) {
//            resize(array2, 2 * size2);
//        }
        if (size2 > 0) {
            T res = array2[start2 + size2 - 1];
            array2[start2 + size2 - 1] =  null;
            size2 -= 1;
            return res;
        } else if (size1 > 0) {
            T res = array1[start1];
            size1 -= 1;
            start1 += 1;
            return res;
        } else {
            return null;
        }
    }

    private void resize(T[] array, int newSize) {
        T[] a = (T[]) new Object[newSize];
        if (array == array1) {
            for (int i = 0; i < Math.min(size1, newSize); i++) {
                a[i] = array[i + start1];
            }
            start1 = 0;
            array1 = a;
        } else {
            for (int i = 0; i < Math.min(size2, newSize); i++) {
                a[i] = array[i + start2];
            }
            start2 = 0;
            array2 = a;
        }
    }

    @Override
    public int size() {
        return size1 + size2;
    }


    @Override
    public T get(int index) {
        if (index >= size1 + size2) {
            return null;
        } else if (index >= size1) {
            return array2[index - size1 + start2];
        }
        return array1[size1 - index - 1 + start1];
    }

    @Override
    public void printDeque() {
        for (int i = start1 + size1 - 1; i >= start1; i--) {
            System.out.print(array1[i] + " ");
        }

        for (int i = start2; i < start2 + size2; i++) {
            System.out.print(array2[i] + " ");
        }

        System.out.println();
    }

    private class MyIterator<T> implements Iterator<T> {
        private int index;
        private int iterSize;
        MyIterator() {
            if (size1 == 0) {
                index = start2;
            } else {
                index = start1 + size1 - 1;
            }
            iterSize = 0;
        }
        @Override
        public boolean hasNext() {
            if (iterSize == size1 + size2) {
                return false;
            }
            return true;
        }

        @Override
        public T next() {
            T res;
            if (iterSize >= size1) {
                res = (T) array2[index];
                index++;
            } else {
                res = (T) array1[index];
                index--;
                if (iterSize + 1 == size1) {
                    index = start2;
                }
            }

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
