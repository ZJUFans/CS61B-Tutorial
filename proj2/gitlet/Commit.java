package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.Map;

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
    private String message;
    private String parent;
    private String date;
    private Map<String, String> containings;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, String> getContainings() {
        return containings;
    }

    public void setContainings(Map<String, String> containins) {
        this.containings = containins;
    }

    /* TODO: fill in the rest of this class. */
    public Commit(String message, String parent, String date, Map<String, String> containins) {
        this.message = message;
        this.parent = parent;
        this.date = date;
        this.containings = containins;
    }

    @Override
    public String toString() {
        return "Commit{" +
                "message='" + message + '\'' +
                ", parent='" + parent + '\'' +
                ", date=" + date +
                ", containings=" + containings +
                '}';
    }
}
