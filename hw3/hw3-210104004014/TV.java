/**
 * Represents a specific type of device: TV.
 * This class implements the {@link Device} interface.
 * Since the execution time is constant for each method, each method has a
 * time complexity of O(1)
 */
public class TV implements Device {

    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new TV with the details below:
     * 
     * @param c the category of the TV
     * @param n the name of the TV
     * @param p the price of the TV
     * @param q the quantity of TV available in stock
     *          This method has a time complexity of O(1)
     */
    TV(String c, String n, double p, int q) {
        category = c;
        name = n;
        price = p;
        quantity = q;
    }

    /**
     * Sets the category of this TV.
     * This method has a time complexity of O(1)
     * 
     * @param c The category to assign to the TV.
     */
    public void setCategory(String c) {
        category = c;
    }

    /**
     * Sets the name of this TV.
     * This method has a time complexity of O(1)
     * 
     * @param n The name to assign to the TV.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the price of this TV.
     * This method has a time complexity of O(1)
     * 
     * @param p The price to assign to the TV.
     */
    public void setPrice(double p) {
        price = p;
    }

    /**
     * Sets the quantity of this TV.
     * This method has a time complexity of O(1)
     * 
     * @param q The quantity to assign to the TV.
     */
    public void setQuantity(int q) {
        quantity = q;
    }

    /**
     * Gets the category of this TV.
     * This method has a time complexity of O(1)
     * 
     * @return The TV's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the name of this TV.
     * This method has a time complexity of O(1)
     * 
     * @return The TV's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of this TV.
     * This method has a time complexity of O(1)
     * 
     * @return The TV's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of this TV.
     * This method has a time complexity of O(1)
     * 
     * @return The TV's quantity.
     */
    public int getQuantity() {
        return quantity;
    }
}
