package gitlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static gitlet.Repository.DATE_FORMAT;

public class Test {

    @org.junit.Test
    public void test2() {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("b", "b");
        map1.put("a", "a");
        map1.put("d", "abc");
        map1.put("c", "c");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("a", "a");
        map2.put("b", "b");
        map2.put("c", "c");
        map2.put("d", "abc");
        System.out.println(map1);
        System.out.println(map2);
        String s1 = Utils.sha1(Utils.serialize(map1));
        String s2 = Utils.sha1(Utils.serialize(map2));
        System.out.println(s1);
        System.out.println(s2);
    }



    @org.junit.Test
    public void test4() throws ParseException {
        String date = DATE_FORMAT.format(new Date());
        System.out.println(date);
    }

}
