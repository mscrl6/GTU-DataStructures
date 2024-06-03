/**
 * Represents a generic device with basic operations.
 * 
 * @author Mustafa Selim Ucaral
 */
public interface Device {

    /**
     * Sets the category of the device ("TV", "Laptop", "Smart Watch", "Smart
     * Phone", "VR Headset").
     *
     * @param c The category to assign to the device
     */
    public void setCategory(String c);

    /**
     * Sets the name of the device (e.g., "LG", "MacBook", "Apple
     * Watch","iPhone","Vision Pro").
     *
     * @param n The name to assign to the device.
     */
    public void setName(String n);

    /**
     * Sets the price of the device.
     *
     * @param p The price to assign to the device.
     */
    public void setPrice(double p);

    /**
     * Sets the quantity of the device available in stock.
     *
     * @param q The quantity to assign to the device.
     */
    public void setQuantity(int q);

    /**
     * Gets the category of the device.
     *
     * @return The device's category.
     */
    public String getCategory();

    /**
     * Gets the name of the device.
     *
     * @return The device's name.
     */
    public String getName();

    /**
     * Gets the price of the device.
     *
     * @return The device's price.
     */
    public double getPrice();

    /**
     * Gets the quantity of the device available in stock.
     *
     * @return The device's quantity.
     */
    public int getQuantity();
}
