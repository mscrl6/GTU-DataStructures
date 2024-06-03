import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is designed to generate random commands related to stock
 * management
 * such as ADD, REMOVE, SEARCH, and UPDATE, and then write these commands to a
 * file.
 */
public class Generator {
    private static final String[] COMMANDS = { "ADD", "REMOVE", "SEARCH", "UPDATE" };
    private static final Random random = new Random();
    private List<String> commands;

    /**
     * Constructor for the Generator class.
     * Initializes the list of commands.
     */
    public Generator() {
        commands = new ArrayList<>();
    }

    /**
     * Generates a specified number of random stock management commands.
     * Each command corresponds to a specific operation on stock data.
     *
     * @param numCommands The number of commands to generate.
     */
    public void generateCommands(int numCommands) {
        for (int i = 0; i < numCommands; i++) {
            int commandIndex = random.nextInt(COMMANDS.length);
            String command = COMMANDS[commandIndex];
            String symbol = "SYM" + random.nextInt(100);

            double price = random.nextDouble() * 100;
            long volume = random.nextInt(1000000);
            long marketCap = random.nextInt(10000000);

            switch (command) {
                case "ADD":
                    commands.add(command + " " + symbol + " " + price + " " + volume + " " + marketCap);
                    break;
                case "SEARCH":
                case "REMOVE":
                    commands.add(command + " " + symbol);
                    break;
                case "UPDATE":
                    String newSymbol = "SYM" + random.nextInt(100);
                    commands.add(
                            command + " " + symbol + " " + newSymbol + " " + price + " " + volume + " " + marketCap);
                    break;
            }
        }
    }

    /**
     * Writes all generated commands to a specified file.
     *
     * @param filename The name of the file to which the commands will be written.
     * @throws IOException If an I/O error occurs when writing to the file.
     */
    public void writeCommandsToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename)) {
            for (String command : commands) {
                writer.write(command + "\n");
            }
        }
    }

    /**
     * Main method to run the command generation and writing process.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Generator generator = new Generator();
        generator.generateCommands(4000);
        try {
            generator.writeCommandsToFile("input.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}