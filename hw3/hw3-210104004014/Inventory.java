import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Represents an inventory of electonics shop.
 * This inventory holds 5 different Device category: TV, Laptop, Smart Phone,
 * Smart Watch and VR Headset
 * It supports operations like adding a new device, deleting, updating,
 * restocking the given device, listing and sorting all the devices, finding the
 * cheapest device and exporting a report about the inventory to txt file
 * 
 */
public class Inventory {
    private LinkedList<ArrayList<Device>> devices = new LinkedList<>();

    /**
     * Constructs an empty inventory with predefined categories for devices.
     */
    Inventory() {
        devices.add(new ArrayList<Device>()); // TV
        devices.add(new ArrayList<Device>()); // Laptop
        devices.add(new ArrayList<Device>()); // Smart Phone
        devices.add(new ArrayList<Device>()); // Smart Watch
        devices.add(new ArrayList<Device>()); // VR Headset
    }

    /**
     * Adds a new device to the inventory if it doesn't already exist.
     * It uses an iterator to find correct ArrayList for the given Category and
     * prints the new device's details if all the inputs are valid
     * 
     * The method calls the isThereADeviceWithGivenName, the time complexity
     * of this method is O(n), where n is the total
     * number of devices. The iterations and additions contribute a constant
     * O(1) complexity.
     * 
     * So, the time complexity of this method is O(n) + O(1) = O(n)
     * 
     * 
     * @param category the category of the device ("TV", "Laptop", "Smart Watch",
     *                 "Smart
     *                 Phone", "VR Headset").
     * @param name     the name of the device
     * @param price    the price of the device
     * @param quantity the quantity of the device to add
     */
    public void addDevice(String category, String name, double price, int quantity) {
        if (price < 0 || quantity < 0) {
            System.err.println("Price or quantity can't be a negative number");
            return;
        }
        if (isThereADeviceWithGivenName(name, "", "", false) == false) {
            Iterator<ArrayList<Device>> iter = devices.iterator();
            switch (category) {
                case "TV": {
                    TV newTV = new TV(category, name, price, quantity);
                    iter.next().add(newTV);
                    break;
                }
                case "Laptop": {
                    Laptop newLaptop = new Laptop(category, name, price, quantity);
                    for (int i = 0; i < 1; ++i) {
                        iter.next();
                    }
                    iter.next().add(newLaptop);
                    break;

                }
                case "Smart Phone": {
                    Smartphone newSmartphone = new Smartphone(category, name, price, quantity);
                    for (int i = 0; i < 2; ++i) {
                        iter.next();
                    }
                    iter.next().add(newSmartphone);
                    break;

                }
                case "Smart Watch": {
                    Smartwatch newSmartwatch = new Smartwatch(category, name, price, quantity);
                    for (int i = 0; i < 3; ++i) {
                        iter.next();
                    }
                    iter.next().add(newSmartwatch);
                    break;

                }
                case "VR Headset": {
                    VRHeadset newVRHeadset = new VRHeadset(category, name, price, quantity);
                    for (int i = 0; i < 4; ++i) {
                        iter.next();
                    }
                    iter.next().add(newVRHeadset);
                    break;

                }
                default:
                    System.err.println("No such category!");
                    return;
            }
            System.out.println(category + ", " +
                    name + ", " +
                    price + "$, " +
                    quantity + " amount added...");
        } else if (isThereADeviceWithGivenName(name, "", "", false) == true) {
            System.err.println("There is already a device named " + name);

        }
    }

    /**
     * Deletes the device with given name.
     * If device deleted correctly prints the device's name.
     * If there are no such device warns the user.
     * 
     * The time complexity of this method is O(n), where n is the total
     * number of devices.
     * In the worst-case scenario, this method iterates through each device to find
     * the given name
     * 
     * @param name the name of the device
     */
    public void removeDevice(String name) {
        Iterator<ArrayList<Device>> LinkedListIterator = devices.iterator();
        while (LinkedListIterator.hasNext()) {
            Iterator<Device> ListIterator = LinkedListIterator.next().listIterator();
            while (ListIterator.hasNext()) {
                Device currentDevice = ListIterator.next();
                if (currentDevice.getName().equals(name)) {
                    System.out.println(name + " removed successfully.");
                    ListIterator.remove();
                    return;
                }
            }
        }
        System.err.println("There are no such device named " + name);
    }

    /**
     * Checks whether there is already a device exist with given name.
     * It also updates the given device if updateTheDevice parameter is true.
     * 
     * The time complexity of this method is O(n), where n is the total
     * number of devices.
     * In the worst case scenario, the operation of accessing and checking the name
     * of a single device has a constant time complexity O(1),since this operation
     * is performed for every device the overall time complexity of the method is
     * O(n)
     * 
     * @param name            the name of the device
     * @param price           the price of the device
     * @param quantity        the quantity of the device
     * @param updateTheDevice a boolean parameter to determine whether it's an
     *                        update operation or not
     * @return true if the device is found, false otherwise
     */

    public boolean isThereADeviceWithGivenName(String name, String price, String quantity, boolean updateTheDevice) {
        Iterator<ArrayList<Device>> LinkedListIterator = devices.iterator();
        while (LinkedListIterator.hasNext()) {
            Iterator<Device> ListIterator = LinkedListIterator.next().listIterator();
            while (ListIterator.hasNext()) {
                Device currentDevice = ListIterator.next();
                if (currentDevice.getName().equals(name)) {
                    if (updateTheDevice == true) {
                        if (price != null) {
                            if (convertStrPriceToDouble(price) < 0) {
                                System.err.println("Price can't be a negative number.");
                                return false;
                            }
                            currentDevice.setPrice(convertStrPriceToDouble(price));
                        }
                        if (quantity != null) {
                            if (Integer.parseInt(quantity) < 0) {
                                System.err.println("Quantity can't be a negative number.");
                                return false;
                            }
                            currentDevice.setQuantity(Integer.parseInt(quantity));
                        }
                        if (price != null || quantity != null) {
                            System.out.println(name + " details updated: "
                                    + "Price - " + currentDevice.getPrice()
                                    + "$, Quantity - " + currentDevice.getQuantity());
                        }
                        return true;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Updates the price and quantity of a device in the inventory by calling the
     * isThereADeviceWithGivenName method.
     *
     * The method calls the isThereADeviceWithGivenName, sı the time complexity
     * of this method is O(n).
     * 
     * @param name     the name of the device to update
     * @param price    the new price of the device
     * @param quantity the new quantity of the device
     */
    public void updateDevice(String name, String price, String quantity) {
        isThereADeviceWithGivenName(name, price, quantity, true);
    }

    /**
     * Lists all devices in the inventory.
     * 
     * The time complexity of this method is O(n), where n is the total
     * number of devices.
     * The operation of accessing and printing the details of a single device has a
     * constant time complexity O(1),since this operation is performed for every
     * device the overall time complexity of the method is O(n)
     * 
     */
    public void ListAll() {
        Iterator<ArrayList<Device>> LinkedListIterator = devices.iterator();
        int count = 1;
        while (LinkedListIterator.hasNext()) {
            Iterator<Device> ListIterator = LinkedListIterator.next().listIterator();
            while (ListIterator.hasNext()) {
                Device currentDevice = ListIterator.next();
                System.out.println(count +
                        ". Category: " + currentDevice.getCategory() +
                        ", Name: " + currentDevice.getName() +
                        ", Price: " + currentDevice.getPrice() +
                        "$, Quantity: " + currentDevice.getQuantity());
                count++;
            }
        }
        if (count == 1) {
            System.out.println("There are no devices to list.");
        }
    }

    /**
     * Converts a string of price to a double.
     * The string should contain a dollar sign.
     *
     * indexOf method's time complexity is O(n), where n is the lenght of
     * the price. Because in the worst case scenario $ is in the end of the string.
     * 
     * substring method's time complexity is O(n), where n is the lenght of
     * the price.
     * parseDouble method's time complexity is O(n), where n is the length of
     * the price.
     * 
     * So, this method's time complexity is O(3n) = O(n)
     * 
     * @param price the price as a string with a dollar sign
     * @return the price as a double
     */
    public double convertStrPriceToDouble(String price) {
        int dollarSign = price.indexOf("$");
        price = price.substring(0, dollarSign);
        double newPrice = Double.parseDouble(price);
        return newPrice;
    }

    /**
     * Finds and prints the cheapest device in the inventory.
     * 
     * The time complexity of this method is O(n), where n is the total
     * number of devices.
     * In the worst case scenario, the operation of accessing and finding the
     * cheapest device has a constant time complexity O(1),since this operation
     * is performed for every device the overall time complexity of the method is
     * O(n)
     */
    public void findCheapest() {
        Iterator<ArrayList<Device>> LinkedListIterator = devices.iterator();
        Iterator<Device> ListIterator = LinkedListIterator.next().listIterator();

        Device currentDevice = ListIterator.next();

        double minPrice = currentDevice.getPrice();

        String category = currentDevice.getCategory();
        String name = currentDevice.getName();
        double price = currentDevice.getPrice();
        int quantity = currentDevice.getQuantity();

        while (LinkedListIterator.hasNext()) {
            while (ListIterator.hasNext()) {
                currentDevice = ListIterator.next();
                if (currentDevice.getPrice() < minPrice) {
                    minPrice = currentDevice.getPrice();
                    category = currentDevice.getCategory();
                    name = currentDevice.getName();
                    price = currentDevice.getPrice();
                    quantity = currentDevice.getQuantity();
                }
            }
            ListIterator = LinkedListIterator.next().listIterator();
        }

        System.out.println("The cheapest device is: ");
        System.out.println("Category: " + category +
                ", Name: " + name +
                ", Price: " + price +
                "$, Quantity: " + quantity);

    }

    /**
     * Sorts the devices in the inventory by their price in ascending order and
     * prints them. It uses bubble sort algorithm.
     * 
     * Where n is the total number of devices:
     * Since the loops iterates over each devices, adding all the devices in a list
     * has O(n) time complexity.
     * 
     * The bubble sort algorithm has O(n^2) time complexity
     * 
     * Printing the devices has a constant time complexity O(1). Since this
     * operation is performed for every device the overall time complexity of the
     * printing the devices is O(n)
     * 
     * So, sortDevices method's time complexity is O(n) + O(n) + O(n^2) = O(n^2)
     */
    public void sortDevices() {
        int totalSize = 0;
        ArrayList<Device> sortedList = new ArrayList<>();
        for (ArrayList<Device> list : devices) {
            for (Device d : list) {
                sortedList.add(d);
                totalSize += 1;
            }
        }

        for (int i = 0; i < totalSize; ++i) {

            for (int j = totalSize - 1; j > i; --j) {
                if (sortedList.get(j - 1).getPrice() > sortedList.get(j).getPrice()) {
                    Device temp = sortedList.get(j - 1);
                    sortedList.set(j - 1, sortedList.get(j));
                    sortedList.set(j, temp);
                }
            }

        }

        int count = 1;
        System.out.println("Devices sorted by price: ");
        for (Device x : sortedList) {
            System.out.println(count +
                    ". Category: " + x.getCategory() +
                    ", Name: " + x.getName() +
                    ", Price: " + x.getPrice() +
                    "$, Quantity: " + x.getQuantity());
            count++;
        }
    }

    /**
     * Calculates the total value of all devices in the inventory.
     * the operation of accessing and adding the price to sum
     * of a single device has a constant time complexity O(1), since this operation
     * is performed for every device the overall time complexity of the method is
     * O(n)
     * 
     * @return the total value of all devices as a double
     */
    public double calculateTotal() {
        double total = 0;
        Iterator<ArrayList<Device>> LinkedListIterator = devices.iterator();
        while (LinkedListIterator.hasNext()) {
            Iterator<Device> ListIterator = LinkedListIterator.next().listIterator();
            while (ListIterator.hasNext()) {
                Device currentDevice = ListIterator.next();
                total += currentDevice.getPrice();
            }
        }
        return total;
    }

    /**
     * Restocks or reduces stock for a device in the inventory.
     *
     * 
     * The time complexity of this method is O(n), where n is the total
     * number of devices.
     * In the worst case scenario, the operation of accessing and checking the name
     * of a single device has a constant time complexity O(1),since this operation
     * is performed for every device the overall time complexity of the method is
     * O(n)
     * 
     * @param name        the name of the device to restock or reduce from stock
     * @param newQuantity the quantity to add or reduce
     * @param addOrRemove true to add the quantity, false to reduce it
     * 
     */
    public void restockDevice(String name, int newQuantity, boolean addOrRemove) {
        Iterator<ArrayList<Device>> LinkedListIterator = devices.iterator();
        while (LinkedListIterator.hasNext()) {
            Iterator<Device> ListIterator = LinkedListIterator.next().listIterator();
            while (ListIterator.hasNext()) {
                Device currentDevice = ListIterator.next();
                if (currentDevice.getName().equals(name)) {
                    if (addOrRemove == false) {
                        if (currentDevice.getQuantity() - newQuantity < 0) {
                            System.err.println("Quantity can't be less than 0");
                            return;
                        }
                        currentDevice.setQuantity(currentDevice.getQuantity() - newQuantity);
                        System.out.println(name + " stock reduced. New quantity: " + currentDevice.getQuantity());
                    } else if (addOrRemove == true) {
                        currentDevice.setQuantity(currentDevice.getQuantity() + newQuantity);
                        System.out.println(name + " restocked. New quantity: " + currentDevice.getQuantity());
                    }

                    return;
                }
            }
        }
        System.err.println("There are no such device named " + name);
    }

    /**
     * Exports the current state of the inventory to a text file named "report.txt".
     * The time complexity of this method is O(n), where n is the total
     * number of devices.
     * The operation of accessing and printing the details of a single device has a
     * constant time complexity O(1),since this operation is performed for every
     * device the overall time complexity of the method is O(n)
     * Other operations has constant time complexity (e.g. file handling)
     * 
     * @throws IOException if there is an error writing to the file
     */
    public void exportInventory() throws IOException {
        File file = new File("report.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fWriter);
        bufferedWriter.write("Electronics Shop Inventory Report\nGenerated on: ");

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        String formattedDate = date.format(formatter);
        bufferedWriter.write(formattedDate);

        bufferedWriter.write("\n\n------------------------------------");
        bufferedWriter.write("-------------------------------------\n");

        bufferedWriter.write(String.format("| %-5s | %-12s | %-20s | %-10s | %-10s |\n", "No.", "Category", "Name",
                "Price", "Quantity"));
        bufferedWriter.write("------------------------------------");
        bufferedWriter.write("-------------------------------------\n");

        int counter = 1;
        for (ArrayList<Device> list : devices) {
            for (Device d : list) {
                String formattedLine = String.format("| %-5d | %-12s | %-20s | $%-9.2f | %-10d |\n",
                        counter, d.getCategory(), d.getName(), d.getPrice(), d.getQuantity());
                bufferedWriter.write(formattedLine);
                counter++;
            }
        }

        bufferedWriter.write("------------------------------------");
        bufferedWriter.write("-------------------------------------\n");

        bufferedWriter.write("\nSummary: \n");

        bufferedWriter.write("- Total Number of Devices: " + --counter);
        bufferedWriter.write("\n- Total Inventory Value: $" + calculateTotal());
        bufferedWriter.write("\n\nEnd of Report\n");
        System.out.println("Report exported.");
        bufferedWriter.close();
    }
}
