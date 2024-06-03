import java.sql.Timestamp;

/**
 * Abstract class representing a generic element in a file system.
 * This class serves as the base for more specific file system elements like
 * files and directories.
 */
public abstract class FileSystemElement {
    protected String name;
    protected Timestamp dateCreated;
    protected FileSystemElement parent;

    /**
     * Constructs a FileSystemElement with a specified name and parent.
     * Sets the creation date to the current system time.
     *
     * @param n the name of the file system element
     * @param p the parent of this element which could be another directory or null
     *          if it is the root
     */
    public FileSystemElement(String n, FileSystemElement p) {
        name = n;
        parent = p;
        dateCreated = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Returns the name of the file system element.
     * 
     * @return the name of this element
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the creation date and time of the file system element.
     * 
     * @return the timestamp of when this element was created
     */
    public Timestamp getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns the parent of this file system element.
     * The parent could be another directory or null if this element does not have a
     * parent (root).
     * 
     * @return the parent element or null if there is no parent
     */
    public FileSystemElement getParent() {
        return parent;
    }
}
