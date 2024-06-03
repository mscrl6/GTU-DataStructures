import java.util.*;

/**
 * Represents a file system that manages a hierarchy of directories and files.
 * The file system has a root directory.
 */
public class FileSystem {
    private Directory root;

    /**
     * Constructor for FileSystem. Initializes the root directory.
     */
    public FileSystem() {
        root = new Directory("root", null);
    }

    /**
     * Returns the root directory of the file system.
     * 
     * @return the root directory
     */
    public Directory getRoot() {
        return root;
    }

    /**
     * Changes the current directory to a new directory specified by the path.
     * 
     * @param path    the full path to the new directory
     * @param current the current directory from which the path will start
     * @return the new directory after the change, or null if the change is
     *         unsuccessful
     */
    public Directory changeDirectory(String path, Directory current, boolean move) {
        if (!path.startsWith("/")) {
            System.err.println("Path must start from the root with using '/'. (e.g. /root/directory1)");
            return null;
        } else if (path.equals(getCurrentPath(current))) {
            System.err.println("You are already in the directory named: " + current.getName());
            return current;
        }
        String[] directories = path.split("/");
        current = root;
        boolean found = false;
        if (path.equals("/root")) {
            return root;
        }
        for (int i = 1; i < directories.length; ++i) {
            found = false;
            for (FileSystemElement x : current.getChildren()) {
                if (x instanceof Directory) {
                    if (x.getName().equals(directories[i])) {
                        current = (Directory) x;
                        found = true;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("No such directory!\n");
            return null;
        }
        if (!move) {
            System.out.println("Directory changed to: " + getCurrentPath(current));
        }
        return current;
    }

    /**
     * Creates a new file in the specified directory.
     * 
     * @param name   the name of the new file
     * @param parent the directory in which the file will be created
     */
    public void createFile(String name, Directory parent) {
        File newFile = new File(name, parent);
        parent.add(newFile, false);
    }

    /**
     * Creates a new directory within the specified parent directory.
     * 
     * @param name   the name of the new directory
     * @param parent the directory in which the new directory will be created
     */
    public void createDirectory(String name, Directory parent) {
        Directory newDirectory = new Directory(name, parent);
        parent.add(newDirectory, false);
    }

    /**
     * Deletes a file with the specified name in the given directory.
     * 
     * @param name   the name of the file to delete
     * @param parent the directory from which the file will be deleted
     */
    public void deleteFile(String name, Directory parent) {
        int i = 0;
        for (FileSystemElement x : parent.getChildren()) {
            if (x instanceof File) {
                if (name.equals(x.getName())) {
                    parent.getChildren().remove(i);
                    System.out.println("File deleted: " + name);
                    return;
                }
            }
            i++;
        }
        if (i == parent.getChildren().size()) {
            System.err.println("There are no such file named " + name);
            return;
        }
    }

    /**
     * Deletes a directory and all of its contents recursively by calling the
     * deleteDirectoryRecursively function.
     * 
     * @param name   the name of the directory to delete
     * @param parent the parent directory from which the directory will be deleted
     */
    public void deleteDirectory(String name, Directory parent) {
        int i = 0;
        for (FileSystemElement x : parent.getChildren()) {
            if (x instanceof Directory) {
                if (name.equals(x.getName())) {
                    deleteDirectoryRecursively(name, (Directory) x);
                    System.out.println("Directory deleted: " + name);
                    parent.getChildren().remove(i);
                    return;
                }
            }
            i++;
        }
        if (i == parent.getChildren().size()) {
            System.err.println("There are no such file named " + name);
            return;
        }
    }

    /**
     * Deletes a directory and its contents recursively.
     * 
     * @param name   the name of the directory to delete
     * @param parent the parent directory containing the directory to be deleted
     */
    private void deleteDirectoryRecursively(String name, Directory parent) {
        if (parent.getChildren().isEmpty()) {
            return;
        }
        for (int i = 0; i < parent.getChildren().size(); ++i) {
            if (parent.getChildren().get(i) instanceof Directory) {
                deleteDirectoryRecursively(name, (Directory) parent.getChildren().get(i));
            }

            /*
             * System.out.println(parent.getChildren().get(i).getName() + " removed!");
             */
            parent.getChildren().remove(i);
        }
    }

    /**
     * Lists all contents of a specified directory.
     * 
     * @param currentDirectory the directory whose contents are to be listed
     */
    public void listContents(Directory currentDirectory) {

        if (currentDirectory.getChildren().isEmpty()) {
            System.err.println("The directory is empty");
            return;
        }
        System.out.println("Listing contents of " + getCurrentPath(currentDirectory));
        for (FileSystemElement x : currentDirectory.getChildren()) {
            if (x instanceof Directory) {
                System.out.println("* " + x.getName() + "/");
            }
        }
        for (FileSystemElement x : currentDirectory.getChildren()) {
            if (x instanceof File) {
                System.out.println(x.getName());
            }
        }
    }

    /**
     * Prints the directory tree starting from the specified directory.
     * 
     * @param currentDirectory the directory from which the tree will be printed
     */
    public void printTree(Directory currentDirectory) {
        int blankCounter = 0;
        Stack<String> directoryNames = new Stack<String>();
        Directory temp = currentDirectory;
        while (currentDirectory != null) {
            directoryNames.push(currentDirectory.name);
            currentDirectory = (Directory) currentDirectory.parent;
        }
        while (!directoryNames.empty()) {
            for (int i = 0; i < blankCounter; ++i) {
                System.out.print(" ");
            }

            System.out.print("* " + directoryNames.peek() + "/");

            if (directoryNames.peek().equals(temp.getName())) {
                System.out.println(" (Current Directory)");
                for (FileSystemElement x : temp.getChildren()) {
                    if (x instanceof Directory) {
                        for (int i = 0; i < blankCounter + 2; ++i) {
                            System.out.print(" ");
                        }
                        System.out.println("*" + x.getName() + "/");
                    }
                }
                for (FileSystemElement x : temp.getChildren()) {
                    if (x instanceof File) {
                        for (int i = 0; i < blankCounter + 2; ++i) {
                            System.out.print(" ");
                        }
                        System.out.println(x.getName());
                    }
                }
            } else {
                System.err.print("\n");
            }
            directoryNames.pop();
            blankCounter += 2;
        }

    }

    /**
     * Gets the full path from the root to the current directory.
     * 
     * @param currentDirectory the directory whose path is required
     * @return a string representing the path
     */
    public String getCurrentPath(Directory currentDirectory) {
        Stack<String> directoryNames = new Stack<String>();
        while (currentDirectory != null) {
            directoryNames.push(currentDirectory.name);
            currentDirectory = (Directory) currentDirectory.parent;
        }
        String path = "";
        while (!directoryNames.empty()) {
            path += "/";
            path += directoryNames.pop();
        }
        return path;
    }

    /**
     * Moves a file or directory to a new location specified by the path.
     * 
     * @param name    the name of the file or directory to move
     * @param path    the new path where the file or directory should be moved
     * @param current the current directory context
     */
    public void moveElement(String name, String path, Directory current) {
        Directory temp = changeDirectory(path, current, true);
        int i = 0;

        for (FileSystemElement x : current.getChildren()) {
            if (x.getName().equals(name)) {
                FileSystemElement tempElement = current.getChildren().remove(i);
                boolean found = temp.add(tempElement, true);
                if (!found) {
                    current.add(tempElement, true);
                }
                if (tempElement instanceof File && found) {
                    System.out.println("File moved: " + name + " to " + path);
                } else if (tempElement instanceof Directory && found) {
                    System.out.println("Directory moved: " + name + " to " + path);
                }
                return;
            }
            i++;
        }
        System.err.println("No such element found");
    }

    /**
     * Searches for a file or directory with the specified name starting from a
     * given directory recursively.
     * 
     * @param name the name of the file or directory to find
     * @param dir  the directory to start the search
     * @return true if the file or directory is found, false otherwise
     */
    public boolean search(String name, Directory dir) {
        for (FileSystemElement x : dir.getChildren()) {
            if (x.getName().equals(name)) {
                System.out.println("Found: " + getCurrentPath((Directory) x.getParent()));
                return true;
            }
        }
        for (int i = 0; i < dir.getChildren().size(); ++i) {
            if (!dir.getChildren().get(i).getName().equals(name)) {
                if (dir.getChildren().get(i) instanceof Directory) {
                    return search(name, (Directory) dir.getChildren().get(i));
                }
            }
        }
        return false;
    }

    /**
     * Sorts the contents of a directory by the date they were created.
     * 
     * @param currentDirectory the directory whose contents are to be sorted
     */
    public void sortDirectory(Directory currentDirectory) {
        System.out.println("Sorted contents of " + getCurrentPath(currentDirectory) + " by date created:");
        ArrayList<FileSystemElement> list = new ArrayList<>();
        for (int i = 0; i < currentDirectory.getChildren().size(); i++) {
            list.add(currentDirectory.getChildren().get(i));
        }
        list.sort((element1, element2) -> element1.getDateCreated().compareTo(element2.getDateCreated()));
        for (FileSystemElement x : currentDirectory.getChildren()) {
            if (x instanceof Directory) {
                System.out.println("* " + x.getName() + "/" + "(" + x.getDateCreated() + ")");
            }
            if (x instanceof File) {
                System.out.println(x.getName() + "(" + x.getDateCreated() + ")");
            }
        }
    }
}