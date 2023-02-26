package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> st = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String str = "";

        for (int i = 0; i < 1000; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform(4);

            if (numberBetweenZeroAndOne == 0) {
                st.addLast(i);
                solution.addLast(i);
                str += "addLast(" + i + ")\n";
            } else if (numberBetweenZeroAndOne == 1){
                st.addFirst(i);
                solution.addFirst(i);
                str += "addFirst(" + i + ")\n";
            } else {
                if (st.size() > 0) {
                    if (numberBetweenZeroAndOne == 2){
                        Integer i1 = st.removeLast();
                        Integer i2 = solution.removeLast();
                        str += "removeLast(): " + i1 + "\n";
                        assertEquals(str, i2, i1);
                    } else if (numberBetweenZeroAndOne == 3){
                        Integer i1 = st.removeFirst();
                        Integer i2 = solution.removeFirst();
                        str += "removeFirst(): " + i1 + "\n";
                        assertEquals(str, i2, i1);
                    }
                }
            }
        }
    }
}
