import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The Person class represents a person in the social network.
 */
public class Person {
    String name;
    int age;
    List<String> hobbies;
    Date timestamp;

    /**
     * Constructs a new Person with the specified name, age, and hobbies.
     *
     * @param name    the name of the person.
     * @param age     the age of the person.
     * @param hobbies the hobbies of the person.
     */
    public Person(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies);
        this.timestamp = new Date();
    }

    /**
     * Returns the formatted timestamp of the person, yyyy-MM-dd HH:mm:ss.
     *
     * @return the formatted timestamp as a String.
     */
    public String getFormattedTimestamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(timestamp);
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Hobbies: " + hobbies + ", Timestamp: " + getFormattedTimestamp() + ")";
    }
}