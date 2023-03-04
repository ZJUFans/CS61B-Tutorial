package gitlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.*;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File COMMIT_DIR = join(GITLET_DIR, "commit");
    public static final File BLOB_DIR = join(GITLET_DIR, "blob");
    public static final File STAGING_ADDITION = join(GITLET_DIR, "addition");
    public static final File STAGING_REMOVAL = join(GITLET_DIR, "removal");

    private static final File Master = join(GITLET_DIR, "master");
    private static final File HEAD = join(GITLET_DIR, "head");
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.US);

    /* TODO: fill in the rest of this class. */

    public static void init() throws IOException {
        if (Master.exists()) {
            throw Utils.error("Control system already exists in the current directory.");
        }

        GITLET_DIR.mkdir();
        COMMIT_DIR.mkdir();
        BLOB_DIR.mkdir();
        STAGING_ADDITION.mkdir();
        STAGING_REMOVAL.mkdir();
        Master.createNewFile();
        HEAD.createNewFile();
        Commit root = new Commit("initial commit", null, DATE_FORMAT.format(new Date(0)), new TreeMap<>());
        writeOperation(root);
    }

    private static void writeOperation(Commit commit) throws IOException {
        writeContents(HEAD, sha1(serialize(commit)));
        writeContents(Master, sha1(serialize(commit)));
        File commitFile = join(COMMIT_DIR, sha1(serialize(commit)));
        commitFile.createNewFile();
        writeObject(commitFile, commit);
    }

    public static void add(String file) throws IOException {
        File f = join(CWD, file);
        if (!f.exists()) {
            throw error("File does not exist.");
        }
        String fUrl = sha1(serialize(f));
        File fAdd = join(STAGING_ADDITION, file);
        if (!fAdd.exists()) {
            fAdd.createNewFile();
            writeContents(fAdd, fUrl);
        } else {
            String oriUrl = readContentsAsString(fAdd);
            if (oriUrl != fUrl) {
                writeContents(fAdd, fUrl);
            }
        }
    }

    private static Commit getHead() {
        String head = readContentsAsString(HEAD);
        Commit commit = readObject(join(COMMIT_DIR, head), Commit.class);
        return commit;
    }


    public static void commit(String message) throws IOException {
        if (plainFilenamesIn(STAGING_ADDITION) == null && plainFilenamesIn(STAGING_REMOVAL) == null) {
            throw error("No changes added to the commit.");
        }
        Commit head = getHead();
        Map<String, String> containings = new TreeMap<>(head.getContainings());
        List<String> files = plainFilenamesIn(STAGING_REMOVAL);
        for (String file : files) {
            String fileUrl = readContentsAsString(join(STAGING_REMOVAL, file));
            if (containings.get(file).equals(fileUrl)) {
                containings.remove(file);
            }
        }
        files = Utils.plainFilenamesIn(STAGING_ADDITION);
        for (String file : files) {
            File fCWD = join(CWD, file);
            String content = readContentsAsString(fCWD);
            String fileUrl = sha1(content);
            File blob = join(BLOB_DIR, fileUrl);
            blob.createNewFile();
            writeContents(blob, content);
            containings.put(file, fileUrl);
        }

        Commit cur = new Commit(message, sha1(serialize(head)), DATE_FORMAT.format(new Date()), containings);
        writeOperation(cur);

        clearStaging();
    }

    private static void clearStaging() {
        File[] adds = STAGING_ADDITION.listFiles();
        for (File file : adds) {
            file.delete();
        }

        File[] rems = STAGING_REMOVAL.listFiles();
        for (File file : rems) {
            file.delete();
        }
    }

    public static void rm(String file) throws IOException {
        Commit head = getHead();
        List<String> adds = plainFilenamesIn(STAGING_ADDITION);
        Map<String, String> containings = head.getContainings();
        if (!adds.contains(file) && !containings.containsKey(file)) {
            throw error("No reason to remove the file.");
        }
        if (adds.contains(file)) {
            File f = join(STAGING_ADDITION, file);
            f.delete();
        }
        File fCWD = join(CWD, file);
        if (containings.containsKey(file)) {
            String fUrl = sha1(serialize(fCWD));
            File fRem = join(STAGING_REMOVAL, file);
            if (!fRem.exists()) {
                fRem.createNewFile();
                Utils.writeContents(fRem, fUrl);
            } else {
                String oriUrl = readContentsAsString(fRem);
                if (oriUrl != fUrl) {
                    Utils.writeContents(fRem, fUrl);
                }
            }
        }
        restrictedDelete(fCWD);
    }

    public static void log() {
        Commit commit = getHead();

        while (true) {
            System.out.println("===");
            System.out.println("commit " + sha1(serialize(commit)));
            System.out.println("Date: " + commit.getDate());
            System.out.println(commit.getMessage());
            System.out.println();
            String parent = commit.getParent();
            if (parent == null) {
                break;
            }
            File fPar = join(COMMIT_DIR, parent);
            commit = readObject(fPar, Commit.class);
        }
    }

    public static void checkoutFile(String file) throws IOException {
        Commit head = getHead();
        checkoutSpecificFile(sha1(serialize(head)), file);
    }

    public static void checkoutSpecificFile(String id, String file) throws IOException {
        File commitFile = join(COMMIT_DIR, id);
        Commit commit = readObject(commitFile, Commit.class);
        Map<String, String> containings = commit.getContainings();
        if (!containings.containsKey(file)) {
            if (id.equals(readContentsAsString(Master))) {
                throw error("File does not exist in that commit.");
            }
            throw error("No commit with that id exists.");
        }
        String fileUrl = containings.get(file);
        File blob = join(BLOB_DIR, fileUrl);
        String content = readContentsAsString(blob);
        File f = join(CWD, file);
        restrictedDelete(f);
        f.createNewFile();
        writeContents(f, content);
    }

}
