/**
 * Represents a specific type of device: VRHeadset.
 * This class implements the {@link Device} interface.
 * Since the execution time is constant for each method, each method has a
 * time complexity of O(1)
 */
public class VRHeadset implements Device {

    private String category;
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new VRHeadset with the details below:
     * 
     * @param c the category of the VRHeadset
     * @param n the name of the VRHeadset
     * @param p the price of the VRHeadset
     * @param q the quantity of VRHeadset available in stock
     *          This method has a time complexity of O(1)
     */
    VRHeadset(String c, String n, double p, int q) {
        category = c;
        name = n;
        price = p;
        quantity = q;
    }

    /**
     * Sets the category of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @param c The category to assign to the VRHeadset.
     */
    public void setCategory(String c) {
        category = c;
    }

    /**
     * Sets the name of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @param n The name to assign to the VRHeadset.
     */
    public void setName(String n) {
        name = n;
    }

    /**
     * Sets the price of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @param p The price to assign to the VRHeadset.
     */
    public void setPrice(double p) {
        price = p;
    }

    /**
     * Sets the quantity of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @param q The quantity to assign to the VRHeadset.
     */
    public void setQuantity(int q) {
        quantity = q;
    }

    /**
     * Gets the category of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @return The VRHeadset's category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the name of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @return The VRHeadset's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @return The VRHeadset's price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the quantity of this VRHeadset.
     * This method has a time complexity of O(1)
     * 
     * @return The VRHeadset's quantity.
     */
    public int getQuantity() {
        return quantity;
    }
}
