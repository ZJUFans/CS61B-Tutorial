package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> myComparator;

    public MaxArrayDeque(Comparator<T> c) {
        myComparator = c;
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
