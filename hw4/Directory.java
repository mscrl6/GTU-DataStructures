import java.util.*;

/**
 * Represents a directory in a file system. A directory can contain other
 * directories and files.
 */
public class Directory extends FileSystemElement {
    private LinkedList<FileSystemElement> children;

    /**
     * Constructs a new Directory with the specified name and parent.
     * 
     * @param name   the name of the directory
     * @param parent the parent file system element (another directory or null if
     *               this is the root)
     */
    public Directory(String name, FileSystemElement parent) {
        super(name, parent);
        children = new LinkedList<FileSystemElement>();
    }

    /**
     * Adds a file system element (file or directory) to this directory.
     * 
     * @param element the file system element to add
     */
    public boolean add(FileSystemElement element, boolean move) {
        for (FileSystemElement x : children) {
            if (x.getName().equals(element.getName())) {
                System.err.println("The name " + element.getName() + " already exist in the directory!");
                return false;
            }
        }
        children.add(element);
        if (!move) {
            if (element instanceof Directory)
                System.out.println("Directory created: " + element.getName());
            else if (element instanceof File)
                System.out.println("File created: " + element.getName());
        }
        return true;

    }

    /**
     * Returns a list of children (files or subdirectories) contained in this
     * directory.
     * 
     * @return a linked list of FileSystemElement representing the contents of the
     *         directory
     */
    public LinkedList<FileSystemElement> getChildren() {
        return children;
    }
}
