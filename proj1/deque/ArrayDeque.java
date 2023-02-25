package deque;

import java.util.Iterator;

public class ArrayDeque<T> {
    T[] array1;
    T[] array2;
    int start1;
    int size1;
    int start2;
    int size2;

    public ArrayDeque() {
        array1 = (T[]) new Object[8];
        array2 = (T[]) new Object[8];
        start1 = 0;
        size1 = 0;
        start2 = 0;
        size2 = 0;
    }

    public void addFirst(T item) {
        if (start1 + size1 >= array1.length - 1) {
            resize(array1, Math.max(size1 * 2, size1 + 8));
        }
        array1[start1 + size1] = item;
        size1 += 1;
    }

    public void addLast(T item) {
        if (start2 + size2 >= array2.length - 1) {
            resize(array2, Math.max(size2 * 2, size2 + 8));
        }
        array2[start2 + size2] = item;
        size2 += 1;
    }

    public T removeFirst() {
        if (array1.length >= 16 && (size1 / array1.length) <= 0.25) {
            resize(array1, (int) (2 * size1));
        }
        if (size1 > 0) {
            T res = array1[start1 + size1 - 1];
            array1[start1 + size1 - 1] = null;
            size1 -= 1;
            return res;
        } else if(size2 > 0) {
            T res = array2[start2];
            size2 -= 1;
            start2 += 1;
            return res;
        } else {
            return null;
        }
    }

    public T removeLast() {
        if (array2.length >= 16 && (size2 / array2.length) <= 0.25) {
            resize(array2, (int) (2 * size2));
        }
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

    public void resize(T[] array, int new_size) {
        T[] a = (T[]) new Object[new_size];
        if (array == array1) {
            for (int i = 0; i < Math.min(size1, new_size); i++) {
                a[i] = array[i + start1];
            }
            start1 = 0;
            array1 = a;
        } else {
            for (int i = 0; i < Math.min(size2, new_size); i++) {
                a[i] = array[i + start2];
            }
            start2 = 0;
            array2 = a;
        }
    }

    public int size() {
        return size1 + size2;
    }

    public boolean isEmpty() {
        return size1 == 0 && size2 == 0;
    }

    public T get(int index) {
        if(index >= size1 + size2) return null;
        if (index >= size1) return array2[index - size1];
        return array1[size1 - index - 1];
    }

    public void printDeque() {
        for (int i = size1 - 1; i >= 0; i--) {
            System.out.print(array1[i] + " ");
        }

        for (int i = 0; i < size2; i++) {
            System.out.print(array2[i] + " ");
        }

        System.out.println();
    }

}
