import java.util.ArrayList;
import java.util.List;

/**
 * Manages stock data using an AVL Tree. This class handles operations such as
 * adding, updating, removing, and searching for stocks, and records the
 * execution time for these operations.
 */
public class StockDataManager {
    private AVLTree avlTree;
    private ArrayList<Long> addTimes;
    private ArrayList<Long> removeTimes;
    private ArrayList<Long> searchTimes;
    private ArrayList<Long> updateTimes;

    /**
     * Constructor for StockDataManager.
     * Initializes an AVL Tree and lists for logging operation times.
     */
    public StockDataManager() {
        avlTree = new AVLTree();
        addTimes = new ArrayList<Long>();
        removeTimes = new ArrayList<Long>();
        searchTimes = new ArrayList<Long>();
        updateTimes = new ArrayList<Long>();
    }

    /**
     * Adds a new stock or updates an existing one based on the provided symbol.
     * Measures the time taken to perform this operation.
     *
     * @param symbol    The stock symbol to add or update.
     * @param price     The price of the stock.
     * @param volume    The volume of stock.
     * @param marketCap The market capitalization of the stock.
     */
    public void addOrUpdateStock(String symbol, double price, long volume, long marketCap) {
        long startTime = System.nanoTime();
        Stock existingStock = avlTree.search(symbol);
        if (existingStock != null) {
            existingStock.setPrice(price);
            existingStock.setVolume(volume);
            existingStock.setMarketCap(marketCap);
        } else {
            Stock newStock = new Stock(symbol, price, volume, marketCap);
            avlTree.insert(newStock);
        }
        long endTime = System.nanoTime();
        addTimes.add(endTime - startTime);
    }

    /**
     * Removes a stock from the AVL Tree by its symbol.
     * Measures the time taken to perform this operation.
     *
     * @param symbol The stock symbol to remove.
     */
    public void removeStock(String symbol) {
        long startTime = System.nanoTime();
        avlTree.delete(symbol);
        long endTime = System.nanoTime();
        removeTimes.add(endTime - startTime);
    }

    /**
     * Searches for a stock by its symbol.
     * Measures the time taken to perform this operation.
     *
     * @param symbol The stock symbol to search.
     * @return The Stock object if found, or null otherwise.
     */
    public Stock searchStock(String symbol) {
        long startTime = System.nanoTime();
        Stock result = avlTree.search(symbol);
        long endTime = System.nanoTime();
        searchTimes.add(endTime - startTime);
        return result;
    }

    /**
     * Updates the details of an existing stock.
     * Measures the time taken to perform this operation.
     *
     * @param symbol       The stock symbol to update.
     * @param newPrice     The new price of the stock.
     * @param newVolume    The new volume of the stock.
     * @param newMarketCap The new market capitalization of the stock.
     */
    public void updateStock(String symbol, double newPrice, long newVolume, long newMarketCap) {
        long startTime = System.nanoTime();
        Stock stock = avlTree.search(symbol);
        if (stock != null) {
            stock.setPrice(newPrice);
            stock.setVolume(newVolume);
            stock.setMarketCap(newMarketCap);
        }
        long endTime = System.nanoTime();
        updateTimes.add(endTime - startTime);
    }

    /**
     * Gets the list of recorded times for add operations.
     * 
     * @return The list of add times.
     */
    public List<Long> getAddTimes() {
        return addTimes;
    }

    /**
     * Gets the list of recorded times for remove operations.
     * 
     * @return The list of remove times.
     */
    public List<Long> getRemoveTimes() {
        return removeTimes;
    }

    /**
     * Gets the list of recorded times for search operations.
     * 
     * @return The list of search times.
     */
    public List<Long> getSearchTimes() {
        return searchTimes;
    }

    /**
     * Gets the list of recorded times for update operations.
     * 
     * @return The list of update times.
     */
    public List<Long> getUpdateTimes() {
        return updateTimes;
    }
}