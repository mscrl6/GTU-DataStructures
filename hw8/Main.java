import java.util.*;

/**
 * The Main class represents the entry point for the Social Network Graph
 */
public class Main {
    /**
     * The main method is the entry point of the application.
     * It initializes the social network and provides a menu for user interaction.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetworkGraph network = new SocialNetworkGraph();
        Person test = new Person("test", 10, Arrays.asList("reading", "cooking"));

        network.addPerson("Yasin Ucaral", 25, Arrays.asList("reading", "cooking"));
        network.addPerson("Fatma Ucaral", 22, Arrays.asList("swimming", "cooking"));
        network.addPerson("Mustafa Ucaral", 27, Arrays.asList("hiking", "painting"));
        network.addPerson("Berkutay Sen", 27, Arrays.asList("hiking", "painting", "football"));
        network.addPerson("Emre Kadioglu", 28, Arrays.asList("running", "swimming"));
        network.addPerson("Omer Akkuzu", 26, Arrays.asList("reading", "hiking"));
        // Adding friendships for demonstration
        network.addFriendship("Mustafa Ucaral", test.getFormattedTimestamp(), "Fatma Ucaral",
                test.getFormattedTimestamp());
        network.addFriendship("Mustafa Ucaral", test.getFormattedTimestamp(), "Yasin Ucaral",
                test.getFormattedTimestamp());
        // network.addFriendship("Mustafa Ucaral", test.getFormattedTimestamp(),
        // "Berkutay Sen",
        // test.getFormattedTimestamp());
        network.addFriendship("Berkutay Sen", test.getFormattedTimestamp(), "Emre Kadioglu",
                test.getFormattedTimestamp());
        network.addFriendship("Omer Akkuzu", test.getFormattedTimestamp(), "Emre Kadioglu",
                test.getFormattedTimestamp());

        // Finding shortest path for demonstration
        network.findShortestPath("Fatma Ucaral", test.getFormattedTimestamp(), "Omer Akkuzu",
                test.getFormattedTimestamp());

        // Counting clusters for demonstration
        network.countClusters();
        while (true) {
            System.out.println("===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter hobbies (comma-separated): ");
                    String hobbiesInput = scanner.nextLine();
                    List<String> hobbies = Arrays.asList(hobbiesInput.split(","));
                    network.addPerson(name, age, hobbies);
                    break;
                case 2:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter timestamp: ");
                    String time = scanner.nextLine();
                    network.removePerson(name, time);
                    break;
                case 3:
                    System.out.print("Enter first person's name: ");
                    String name1 = scanner.nextLine();
                    System.out.print("Enter first person's  timestamp: ");
                    String time1 = scanner.nextLine();
                    System.out.print("Enter second person's name: ");
                    String name2 = scanner.nextLine();
                    System.out.print("Enter second person's  timestamp: ");
                    String time2 = scanner.nextLine();
                    network.addFriendship(name1, time1, name2, time2);
                    break;
                case 4:
                    System.out.print("Enter first person's name: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter first person's  timestamp: ");
                    time1 = scanner.nextLine();
                    System.out.print("Enter second person's name: ");
                    name2 = scanner.nextLine();
                    System.out.print("Enter second person's  timestamp: ");
                    time2 = scanner.nextLine();
                    network.removeFriendship(name1, time1, name2, time2);
                    break;
                case 5:
                    System.out.print("Enter first person's name: ");
                    name1 = scanner.nextLine();
                    System.out.print("Enter first person's  timestamp: ");
                    time1 = scanner.nextLine();
                    System.out.print("Enter second person's name: ");
                    name2 = scanner.nextLine();
                    System.out.print("Enter second person's  timestamp: ");
                    time2 = scanner.nextLine();
                    network.findShortestPath(name1, time1, name2, time2);
                    break;
                case 6:
                    System.out.print("Enter person's name: ");
                    String suggestName = scanner.nextLine();
                    System.out.print("Enter person's  timestamp: ");
                    time1 = scanner.nextLine();
                    System.out.print("Enter maximum number of friends to suggest: ");
                    int maxSuggestions = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    network.suggestFriends(suggestName, maxSuggestions, time1);
                    break;
                case 7:
                    network.countClusters();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
