package randomizedtest;

import afu.org.checkerframework.checker.igj.qual.I;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> al = new AListNoResizing<>();
        BuggyAList<Integer> bl = new BuggyAList<>();
        al.addLast(4);
        al.addLast(5);
        al.addLast(6);
        bl.addLast(4);
        bl.addLast(5);
        bl.addLast(6);
        assertEquals(al.removeLast(), bl.removeLast());
        assertEquals(al.removeLast(), bl.removeLast());
        assertEquals(al.removeLast(), bl.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 4000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(size, B.size());
            } else {
                if (L.size() > 0) {
                    if (operationNumber == 2) {
                        Integer last = L.getLast();
                        assertEquals(last, B.getLast());
                    } else if (operationNumber == 3) {
                        Integer last = L.removeLast();
                        assertEquals(last, B.removeLast());
                    }
                }
            }
        }
    }
}
