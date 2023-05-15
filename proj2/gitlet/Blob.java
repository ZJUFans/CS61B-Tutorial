package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;
import static gitlet.MyUtils.*;

public class Blob implements Serializable {
    private final File source;
    private final byte[] content;
    private final String id;
    private final File file;

    public Blob(File sourceFile) {
        source = sourceFile;
        String filePath = sourceFile.getPath();
        content = readContents(sourceFile);
        id = sha1(filePath, content);
        file = getObjectFile(id);
    }

    public String getId() {
        return id;
    }

    public File getFile() {
        return file;
    }
}
