/**
 * Represents a file in a file system. This class extends FileSystemElement to
 * provide specific characteristics and behaviors for files.
 */
public class File extends FileSystemElement {
    /**
     * Constructs a new File with the specified name and parent directory.
     *
     * @param name   the name of the file
     * @param parent the parent file system element, a directory
     */
    public File(String name, FileSystemElement parent) {
        super(name, parent);
    }
}
