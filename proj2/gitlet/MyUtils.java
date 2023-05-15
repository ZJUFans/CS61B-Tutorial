package gitlet;

import java.io.File;
import static gitlet.Utils.join;

public class MyUtils {
    public static File getObjectFile(String id) {
        String dirName = getObjectDirName(id);
        String fileName = getObjectFileName(id);
        return join(Repository.OBJECTS_DIR, dirName, fileName);
    }

    public static String getObjectDirName(String id) {
        return id.substring(0, 2);
    }

    public static String getObjectFileName(String id) {
        return id.substring(2);
    }

}
