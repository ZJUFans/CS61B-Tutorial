package deque;

import java.lang.*;
import java.util.Comparator;

import org.junit.Test;

public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        MaxArrayDeque<Integer> deque = new MaxArrayDeque<>(new MyComparator<>());
        for (int i = 0; i < 100; i++) {
            deque.addFirst(i);
            deque.addLast(i);
        }
        System.out.println(deque.max());
    }

    private class MyComparator<Integer> implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            int i1 = (int) o1;
            int i2 = (int) o2;
            return i1 - i2;
        }
    }
}
