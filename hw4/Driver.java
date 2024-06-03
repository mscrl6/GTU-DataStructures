import java.util.Scanner;

/**
 * A command-line interface to manage a file system. Provides a menu to perform
 * operations like changing directories, listing contents, creating or deleting
 * files and directories, and more.
 */
public class Driver {
    public static FileSystem fs = new FileSystem();
    public static Directory currentDirectory = fs.getRoot();
    public static Scanner scan = new Scanner(System.in);

    /**
     * The main method that starts the file system management application.
     * Displays a menu of choices and processes user input to manage the file
     * system.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        System.out.println("==== File System Management Menu ====");
        System.out.println("1.  Change Directory");
        System.out.println("2.  List Directory Contents");
        System.out.println("3.  Create File");
        System.out.println("4.  Create Directory");
        System.out.println("5.  Delete File");
        System.out.println("6.  Delete Directory");
        System.out.println("7.  Move File/Directory");
        System.out.println("8.  Search File/Directory");
        System.out.println("9.  Print Directory Tree");
        System.out.println("10. Sort Contents by Date");
        System.out.println("11. Exit");
        while (true) {
            System.out.println("\n\n>Current Directory -> " + fs.getCurrentPath(currentDirectory));
            System.out.print("Please select an option: ");

            String optionString = scan.nextLine();
            try {
                int option = Integer.parseInt(optionString);
                switch (option) {
                    case 1:
                        changeDirectory();
                        break;
                    case 2:
                        fs.listContents(currentDirectory);
                        break;
                    case 3:
                        createFile();
                        break;
                    case 4:
                        createDirectory();
                        break;
                    case 5:
                        deleteFile();
                        break;
                    case 6:
                        deleteDirectory();
                        break;
                    case 7:
                        moveOperations();
                        break;
                    case 8:
                        System.out.print("Search query: ");
                        String query = scan.nextLine();
                        System.out.println("Searching from root...");
                        boolean isFound = fs.search(query, fs.getRoot());
                        if (!isFound) {
                            System.out.println(query + " is not in the file system.");
                        }
                        break;
                    case 9:
                        fs.printTree(currentDirectory);
                        break;
                    case 10:
                        fs.sortDirectory(currentDirectory);
                        break;
                    case 11:
                        System.out.println("Terminating...");
                        scan.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");

                }
            } catch (Exception e) {
                System.out.println("Invalid option. Please try again.");
            }

        }
    }

    /**
     * Handles the logic to change the current working directory.
     * It prompts the user for a new directory path and attempts to change to it.
     */
    private static void changeDirectory() {
        while (true) {
            System.out.println("Current directory: " + fs.getCurrentPath(currentDirectory));
            System.out.print("Enter new directory path: ");
            String newPath = scan.nextLine();
            Directory newDirectory = fs.changeDirectory(newPath, currentDirectory, false);
            if (newDirectory == currentDirectory) {
                break;
            }
            if (newDirectory == fs.getRoot()) {
                System.out.println("Directory changed to: /root");
            }
            if (newDirectory != null && newDirectory != currentDirectory) {
                currentDirectory = newDirectory;
                break;
            }
        }

    }

    /**
     * Prompts the user for a file name and creates a file in the current directory
     * if the name is valid.
     */
    private static void createFile() {
        while (true) {
            System.out.print("Enter the file name to create (e.g.: file.pdf, text.txt): ");
            String name = scan.nextLine();
            if (name.length() > 4 && (name.charAt(name.length() - 4) == '.'
                    || (name.length() > 5 && name.charAt(name.length() - 5) == '.'))) {
                fs.createFile(name, currentDirectory);
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    /**
     * Prompts the user for a directory name and creates a directory in the current
     * directory if the name is valid.
     */
    private static void createDirectory() {
        while (true) {
            System.out.print("Enter the directory name to create (e.g.: dir1, project): ");
            String name = scan.nextLine();
            if (name.contains("/") || name.contains("\\") || name.contains("*")
                    || name.contains("?") || name.contains("|")
                    || name.contains("<") || name.contains(">")) {
                System.out.println("Invalid input. Please try again.");
            } else {
                fs.createDirectory(name, currentDirectory);
                break;
            }
        }
    }

    /**
     * Prompts the user for a file name and deletes the file in the current
     * directory if it's exist.
     */
    private static void deleteFile() {
        System.out.print("Enter the file name to delete: ");
        String name = scan.nextLine();
        fs.deleteFile(name, currentDirectory);
    }

    /**
     * Prompts the user for a directory name and deletes the directory in the
     * current
     * directory if it's exist.
     */
    private static void deleteDirectory() {
        System.out.print("Enter the directory name to delete: ");
        String name = scan.nextLine();
        fs.deleteDirectory(name, currentDirectory);
    }

    /**
     * Handles the logic to move a file or directory to a new location.
     * Prompts the user for the name of the file/directory and the new path.
     */
    private static void moveOperations() {
        System.out.print("Enter the name of the file/directory to move: ");
        String name = scan.nextLine();
        System.out.print("Enter the new directory path: ");
        String path = scan.nextLine();
        fs.moveElement(name, path, currentDirectory);
    }

}
