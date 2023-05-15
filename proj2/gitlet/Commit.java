package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import static gitlet.Utils.*;
import static gitlet.MyUtils.*;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */

    private final Date date;
    private final String message;
    private final List<String> parents;
    private final Map<String, String> tracked;
    private final String id;
    private final File file;

    public Commit(String message, List<String> parents, Map<String, String> tracked) {
        date = new Date();
        this.message = message;
        this.parents = parents;
        this.tracked = tracked;
        id = generateId();
        file = getObjectFile(id);
    }

    public Commit() {
        date = new Date(0);
        message = "initial commit";
        parents = new ArrayList<>();
        tracked = new HashMap<>();
        id = generateId();
        file = getObjectFile(id);
    }

    private String generateId() {
        return sha1(getTimestamp(), message, parents.toString(), tracked.toString());
    }

    public String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z", Locale.US);
        return dateFormat.format(date);
    }

    public static Commit fromFile(String id) {
        return readObject(getObjectFile(id), Commit.class);
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getParents() {
        return parents;
    }

    public Map<String, String> getTracked() {
        return tracked;
    }
}
