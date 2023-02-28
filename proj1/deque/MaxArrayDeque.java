package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> myComparator;

    public MaxArrayDeque(Comparator<T> c) {
        myComparator = c;
        ArrayDeque<T> deque = new ArrayDeque<>();
    }

    public T max() {
        return max(myComparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int index = 0;
        while (true) {
            if (get(index) != null) {
                break;
            }
            index++;
        }
        T res = get(index);
        for (int i = index + 1; i < size() + index; i++) {
            int cp = c.compare(get(i), res);
            if (cp > 0) {
                res = get(i);
            }
        }
        return res;
    }
}
