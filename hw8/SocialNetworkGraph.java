import java.util.*;

/**
 * The SocialNetworkGraph class represents a social network graph
 * with methods to manage people and friendships, find shortest paths,
 * count clusters, and suggest friends.
 */
public class SocialNetworkGraph {
    Map<String, Person> people = new HashMap<>();
    Map<Person, List<Person>> friendships = new HashMap<>();

    /**
     * Adds a person to the social network.
     *
     * @param name    the name of the person.
     * @param age     the age of the person.
     * @param hobbies the hobbies of the person.
     */
    public void addPerson(String name, int age, List<String> hobbies) {
        Person person = new Person(name, age, hobbies);
        people.put(name + person.getFormattedTimestamp(), person);
        friendships.put(person, new ArrayList<>());
        System.out.println("Person added: " + person);
    }

    /**
     * Removes a person from the social network.
     *
     * @param name the name of the person to be removed.
     * @param time the timestamp of the person to be removed.
     */
    public void removePerson(String name, String time) {
        Person person = people.get(name + time);
        if (person == null || !person.getFormattedTimestamp().equals(time)) {
            System.out.println("Person not found in the network.");
            return;
        }
        people.remove(name + time);
        friendships.remove(person);
        for (List<Person> friendsList : friendships.values()) {
            friendsList.remove(person);
        }
        System.out.println("Person removed: " + person.name);
    }

    /**
     * Adds a friendship between two people in the social network.
     *
     * @param name1 the name of the first person.
     * @param time1 the timestamp of the first person.
     * @param name2 the name of the second person.
     * @param time2 the timestamp of the second person.
     */
    public void addFriendship(String name1, String time1, String name2, String time2) {
        Person person1 = people.get(name1 + time1);
        Person person2 = people.get(name2 + time2);
        if (person1 == null || person2 == null ||
                !person1.getFormattedTimestamp().equals(time1) ||
                !person2.getFormattedTimestamp().equals(time2)) {
            System.out.println("One or both persons not found in the network.");
            return;
        }
        friendships.get(person1).add(person2);
        friendships.get(person2).add(person1);
        System.out.println("Friendship added between " + person1.name + " and " + person2.name);
    }

    /**
     * Removes a friendship between two people in the social network.
     *
     * @param name1 the name of the first person.
     * @param time1 the timestamp of the first person.
     * @param name2 the name of the second person.
     * @param time2 the timestamp of the second person.
     */
    public void removeFriendship(String name1, String time1, String name2, String time2) {
        Person person1 = people.get(name1 + time1);
        Person person2 = people.get(name2 + time2);
        if (person1 == null || person2 == null ||
                !person1.getFormattedTimestamp().equals(time1) ||
                !person2.getFormattedTimestamp().equals(time2)) {
            System.out.println("One or both persons not found in the network.");
            return;
        }
        friendships.get(person1).remove(person2);
        friendships.get(person2).remove(person1);
        System.out.println("Friendship removed between " + person1.name + " and " + person2.name);
    }

    /**
     * Finds the shortest path between two people in the social network.
     *
     * @param startName the name of the starting person.
     * @param time1     the timestamp of the starting person.
     * @param endName   the name of the ending person.
     * @param time2     the timestamp of the ending person.
     */
    public void findShortestPath(String startName, String time1, String endName, String time2) {
        Person start = people.get(startName + time1);
        Person end = people.get(endName + time2);
        if (start == null || end == null ||
                !start.getFormattedTimestamp().equals(time1) ||
                !end.getFormattedTimestamp().equals(time2)) {
            System.out.println("One or both persons not found in the network.");
            return;
        }
        if (start != null && end != null) {
            Queue<Person> queue = new LinkedList<>();
            Map<Person, Person> prev = new HashMap<>();
            Set<Person> visited = new HashSet<>();

            queue.add(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                Person current = queue.poll();

                if (current.equals(end)) {
                    printPath(start, end, prev);
                    return;
                }

                for (Person neighbor : friendships.get(current)) {
                    if (!visited.contains(neighbor)) {
                        queue.add(neighbor);
                        visited.add(neighbor);
                        prev.put(neighbor, current);
                    }
                }
            }

        }
        System.out.println("No path found between " + startName + " and " + endName);
    }

    /**
     * Prints the shortest path between two people.
     *
     * @param start the starting person.
     * @param end   the ending person.
     * @param prev  the map of previous nodes in the path.
     */
    private void printPath(Person start, Person end, Map<Person, Person> prev) {
        List<String> pathName = new ArrayList<>();
        for (Person at = end; at != null; at = prev.get(at)) {
            pathName.add(at.name);
        }
        Collections.reverse(pathName);
        System.out.println("Shortest path: " + pathName);
    }

    /**
     * Counts the number of clusters (connected components) in the social network.
     */
    public void countClusters() {
        Set<Person> visited = new HashSet<>();
        int clusterCount = 0;

        for (Person person : people.values()) {
            if (!visited.contains(person)) {
                List<Person> cluster = new ArrayList<>();
                bfs(person, visited, cluster);
                clusterCount++;
                System.out.println("\nCluster " + clusterCount + ": ");
                for (Person p : cluster) {
                    System.out.println(p.name);
                }
            }
        }

        System.out.println("Number of clusters found: " + clusterCount);
    }

    /**
     * Performs a breadth-first search (BFS) starting from a given person.
     *
     * @param start   the starting person.
     * @param visited the set of visited people.
     * @param cluster the list of people in the current cluster.
     */
    private void bfs(Person start, Set<Person> visited, List<Person> cluster) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Person current = queue.poll();
            cluster.add(current);

            for (Person neighbor : friendships.get(current)) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
    }

    /**
     * Suggests friends for a given person based on mutual friends and common
     * hobbies.
     *
     * @param name           the name of the person.
     * @param maxSuggestions the maximum number of friends to suggest.
     * @param time           the timestamp of the person.
     */
    public void suggestFriends(String name, int maxSuggestions, String time) {
        Person person = people.get(name + time);
        if (person == null) {
            System.out.println("Person not found in the network.");
            return;
        }
        if (!person.getFormattedTimestamp().equals(time)) {
            System.out.println("Person not found in the network.");
            return;
        }
        Map<Person, Double> scores = new HashMap<>();
        Map<Person, Integer> mutualFriendsCount = new HashMap<>();
        Map<Person, Integer> commonHobbiesCount = new HashMap<>();

        for (Person p : people.values()) {
            if (!p.equals(person) && !friendships.get(person).contains(p)) {
                int mutualFriends = 0;
                int commonHobbies = 0;

                for (Person friend : friendships.get(person)) {
                    if (friendships.get(p).contains(friend)) {
                        mutualFriends++;
                    }
                }

                for (String hobby : person.hobbies) {
                    if (p.hobbies.contains(hobby)) {
                        commonHobbies++;
                    }
                }

                double score = mutualFriends * 1 + commonHobbies * 0.5;
                scores.put(p, score);
                mutualFriendsCount.put(p, mutualFriends);
                commonHobbiesCount.put(p, commonHobbies);
            }
        }

        List<Map.Entry<Person, Double>> scoreList = new ArrayList<>(scores.entrySet());
        int n = scoreList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (scoreList.get(j).getValue() < scoreList.get(j + 1).getValue()) {
                    Map.Entry<Person, Double> temp = scoreList.get(j);
                    scoreList.set(j, scoreList.get(j + 1));
                    scoreList.set(j + 1, temp);
                }
            }
        }

        System.out.println("Suggested friends for " + person.name + ":");
        for (int i = 0; i < (maxSuggestions < scoreList.size() ? maxSuggestions : scoreList.size()); i++) {
            Map.Entry<Person, Double> entry = scoreList.get(i);
            Person p = entry.getKey();
            double score = entry.getValue();
            if (score != 0) {
                int mutualFriends = mutualFriendsCount.get(p);
                int commonHobbies = commonHobbiesCount.get(p);
                System.out.println(
                        p.name + " (Score: " + score + ", " + mutualFriends + " mutual friends, " + commonHobbies
                                + " common hobbies)");
            }

        }
    }
}