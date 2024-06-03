import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class to execute operations on stock data from an input file and analyze
 * performance.
 */
public class Main {
    /**
     * The main method initializes processing of commands from a file and performs
     * performance analysis.
     *
     * @param args Command line arguments where the first argument should be the
     *             input file name.
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }
        String inputFile = args[0];
        StockDataManager manager = new StockDataManager();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        performPerformanceAnalysis(manager, 1000);
        visualizePerformance(manager);
    }

    /**
     * Processes a single line command from the input file.
     *
     * @param line    The command line containing the operation and its parameters.
     * @param manager The stock data manager to execute operations.
     */
    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch (command) {
            case "ADD":
                manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]),
                        Long.parseLong(tokens[4]));
                break;
            case "REMOVE":
                manager.removeStock(tokens[1]);
                break;
            case "SEARCH":
                Stock stock = manager.searchStock(tokens[1]);
                if (stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
                break;
            case "UPDATE":
                manager.updateStock(tokens[1], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]),
                        Long.parseLong(tokens[5]));
                break;
            default:
                System.out.println("Unknown command: " + command);
                break;
        }

    }

    /**
     * Performs a performance analysis by repeatedly executing stock operations to
     * calculate the average execution times.
     *
     * @param manager The stock data manager on which operations are performed.
     * @param size    The number of iterations for each operation to compute average
     *                times.
     */
    private static void performPerformanceAnalysis(StockDataManager manager, int size) {
        long startTime, endTime;

        // Measure time for ADD operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.addOrUpdateStock("SYM" + i, Math.random() * 100, (long) (Math.random() * 1000000),
                    (long) (Math.random() * 1000000000));
        }
        endTime = System.nanoTime();
        System.out.println("Average ADD time: " + (endTime - startTime) / size + " ns");

        // Measure time for SEARCH operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.searchStock("SYM" + i);
        }
        endTime = System.nanoTime();
        System.out.println("Average SEARCH time: " + (endTime - startTime) / size + " ns");

        // Measure time for REMOVE operation
        startTime = System.nanoTime();
        for (int i = 0; i < size; i++) {
            manager.removeStock("SYM" + i);
        }
        endTime = System.nanoTime();
        System.out.println("Average REMOVE time: " + (endTime - startTime) / size + " ns");
    }

    /**
     * Visualizes the performance analysis of stock operations using a graphical
     * representation.
     *
     * @param manager The stock data manager whose operation performance is to be
     *                visualized.
     */
    private static void visualizePerformance(StockDataManager manager) {
        ArrayList<Long> addTimes = new ArrayList<>();
        ArrayList<Long> removeTimes = new ArrayList<>();
        ArrayList<Long> searchTimes = new ArrayList<>();
        ArrayList<Long> updateTimes = new ArrayList<>();

        // Select data points at positions that are powers of 2
        for (int i = 10; i < manager.getAddTimes().size(); i += 100) {
            addTimes.add(manager.getAddTimes().get(i));
        }
        for (int i = 10; i < manager.getRemoveTimes().size(); i += 100) {
            removeTimes.add(manager.getRemoveTimes().get(i));
        }
        for (int i = 10; i < manager.getSearchTimes().size(); i += 100) {
            searchTimes.add(manager.getSearchTimes().get(i));
        }
        for (int i = 10; i < manager.getUpdateTimes().size(); i += 100) {
            updateTimes.add(manager.getUpdateTimes().get(i));
        }

        visualize("ADD Operation Performance", addTimes);
        visualize("REMOVE Operation Performance", removeTimes);
        visualize("SEARCH Operation Performance", searchTimes);
        visualize("UPDATE Operation Performance", updateTimes);
    }

    /**
     * Configures and displays a GUI visualization frame for given data.
     *
     * @param title The title for the visualization window.
     * @param times A list of times for the operations to be plotted.
     */
    private static void visualize(String title, ArrayList<Long> times) {
        GUIVisualization frame = new GUIVisualization("line", title);
        for (int i = 0; i < times.size(); i++) {
            frame.addDataPoint(i, times.get(i));
        }
        frame.setVisible(true);
    }
}