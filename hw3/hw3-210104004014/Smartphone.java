/**
 * Represents a specific type of device: Smartphone.
 * This class implements the {@link Device} interface.
 * Since the execution time is constant for each method, each method has a
 * time complexity of O(1)
 */
public class Smartphone implements Device {

    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Smartphone with the details below:
     * 
     * @param c the category of the Smartphone
     * @param n the name of the Smartphone
     * @param p the price of the Smartphone
     * @param q the quantity of Smartphone available in stock
     *          This method has a time complexity of O(1)
     */
    Smartphone(String c, String n, double p, int q) {
        category = c;
        name = n;
        price = p;
        quantity = q;
    }

    /**
     * Sets the category of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @param c The category to assign to the Smartphone.
     */
    public void setCategory(String c) {
        category = c;
    }

    /**
     * Sets the name of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @param n The name to assign to the Smartphone.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the price of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @param p The price to assign to the Smartphone.
     */
    public void setPrice(double p) {
        price = p;
    }

    /**
     * Sets the quantity of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @param q The quantity to assign to the Smartphone.
     */
    public void setQuantity(int q) {
        quantity = q;
    }

    /**
     * Gets the category of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartphone's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the name of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartphone's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartphone's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of this Smartphone.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartphone's quantity.
     */
    public int getQuantity() {
        return quantity;
    }
}
