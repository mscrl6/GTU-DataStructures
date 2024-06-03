/**
 * Represents a specific type of device: Laptop.
 * This class implements the {@link Device} interface.
 * Since the execution time is constant for each method, each method has a
 * time complexity of O(1)
 */
public class Laptop implements Device {

    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Laptop with the details below:
     * 
     * @param c the category of the laptop
     * @param n the name of the laptop
     * @param p the price of the laptop
     * @param q the quantity of laptops available in stock
     *          This method has a time complexity of O(1)
     */
    Laptop(String c, String n, double p, int q) {
        category = c;
        name = n;
        price = p;
        quantity = q;
    }

    /**
     * Sets the category of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @param c The category to assign to the Laptop.
     */
    public void setCategory(String c) {
        category = c;
    }

    /**
     * Sets the name of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @param n The name to assign to the Laptop.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the price of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @param p The price to assign to the Laptop.
     */
    public void setPrice(double p) {
        price = p;
    }

    /**
     * Sets the quantity of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @param q The quantity to assign to the Laptop.
     */
    public void setQuantity(int q) {
        quantity = q;
    }

    /**
     * Gets the category of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @return The Laptop's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the name of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @return The Laptop's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @return The Laptop's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of this laptop.
     * This method has a time complexity of O(1)
     * 
     * @return The Laptop's quantity.
     */
    public int getQuantity() {
        return quantity;
    }
}
