/**
 * Represents a specific type of device: Smartwatch.
 * This class implements the {@link Device} interface.
 * Since the execution time is constant for each method, each method has a
 * time complexity of O(1)
 */
public class Smartwatch implements Device {

    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Smartwatch with the details below:
     * 
     * @param c the category of the Smartwatch
     * @param n the name of the Smartwatch
     * @param p the price of the Smartwatch
     * @param q the quantity of Smartwatch available in stock
     *          This method has a time complexity of O(1)
     */
    Smartwatch(String c, String n, double p, int q) {
        category = c;
        name = n;
        price = p;
        quantity = q;
    }

    /**
     * Sets the category of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @param c The category to assign to the Smartwatch.
     */
    public void setCategory(String c) {
        category = c;
    }

    /**
     * Sets the name of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @param n The name to assign to the Smartwatch.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the price of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @param p The price to assign to the Smartwatch.
     */
    public void setPrice(double p) {
        price = p;
    }

    /**
     * Sets the quantity of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @param q The quantity to assign to the Smartwatch.
     */
    public void setQuantity(int q) {
        quantity = q;
    }

    /**
     * Gets the category of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartwatch's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the name of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartwatch's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartwatch's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of this Smartwatch.
     * This method has a time complexity of O(1)
     * 
     * @return The Smartwatch's quantity.
     */
    public int getQuantity() {
        return quantity;
    }
}
