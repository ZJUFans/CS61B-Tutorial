package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> myComparator;
    private T[] array1;
    private T[] array2;
    private int start1;
    private int size1;
    private int start2;
    private int size2;
    public MaxArrayDeque(Comparator<T> c) {
        myComparator = c;
        ArrayDeque<T> deque = new ArrayDeque<>();
        array1 = deque.getArray1();;
        array2 = deque.getArray2();
        start1 = deque.getStart1();
        start2 = deque.getStart2();
        size1 = deque.getSize1();
        size2 = deque.getSize2();
    }

    public T max() {
        return max(myComparator);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T res = null;

        for (int i = start1; i < start1 + size1; i++) {
            if (res == null) {
                res = array1[i];
                continue;
            }
            int compare = c.compare(array1[i], res);
            if (compare > 0) {
                res = array1[i];
            }
        }

        for (int i = start2; i < start2 + size2; i++) {
            if (res == null) {
                res = array2[i];
                continue;
            }
            int compare = c.compare(array2[i], res);
            if (compare > 0) {
                res = array2[i];
            }
        }

        return res;

    }
}
