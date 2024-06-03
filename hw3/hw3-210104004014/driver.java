import java.io.IOException;
import java.util.Scanner;

/**
 * The main driver class for the Electronics Inventory Management System.
 * It provides a text-based menu to navigate through the system’s
 * functionalities
 * Users can add, remove, update, list, find the cheapest, sort, calculate total
 * value, restock, export inventory report through menu options
 *
 */

public class driver {
    /**
     * The main method to run the Electronics Inventory Management System. It
     * initializes the system and processes user until the user exit the program.
     *
     * @param args Command line arguments
     * @throws IOException if there is an error writing to the file
     */
    public static void main(String[] args) throws IOException {
        int option;
        String category;
        String name;
        String price;
        int quantity;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Electronics Inventory Management System!");
        System.out.print("Please select an option:\n" +
                "1. Add a new device\n" +
                "2. Remove a device\n" +
                "3. Update device details\n" +
                "4. List all devices\n" +
                "5. Find the cheapest device\n" +
                "6. Sort devices by price\n" +
                "7. Calculate total inventory value\n" +
                "8. Restock a device\n" +
                "9. Export inventory report\n" +
                "0. Exit\n");
        option = 1;
        Inventory newInventory = new Inventory();
        while (option != 0) {
            System.out.print(">Please enter the option: ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1: {
                    System.out.print("Enter category name: ");
                    category = scanner.nextLine();
                    System.out.print("Enter device name: ");
                    name = scanner.nextLine();
                    while (true) {
                        System.out.print("Enter price (e.g., 99.99$ or 500$): ");
                        price = scanner.nextLine();
                        if (isValidPriceFormat(price)) {
                            break;
                        } else {
                            System.out.println("Invalid price format. Please try again.");
                        }
                    }
                    while (true) {
                        System.out.print("Enter quantity: ");
                        try {
                            quantity = Integer.parseInt(scanner.nextLine());
                            if (quantity < 0) {
                                throw new NumberFormatException();
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid quantity. Please try again.");
                        }
                    }
                    newInventory.addDevice(category, name, newInventory.convertStrPriceToDouble(price), quantity);
                    break;
                }
                case 2: {
                    System.out.print("Enter the name of the device to remove: ");
                    name = scanner.nextLine();
                    newInventory.removeDevice(name);
                    break;
                }
                case 3: {
                    System.out.print("Enter the name of the device to update: ");
                    name = scanner.nextLine();

                    String newPriceStr;
                    Boolean updatePrice = false;

                    while (true) {
                        System.out.print("Enter new price (leave blank to keep current price): ");
                        newPriceStr = scanner.nextLine();

                        if (newPriceStr.isEmpty()) {
                            break;
                        } else if (isValidPriceFormat(newPriceStr)) {
                            updatePrice = true;
                            break;
                        } else if (!isValidPriceFormat(newPriceStr)) {
                            System.err.println("Invalid price format. Please try again.");
                        }
                    }

                    String newQuantityStr;
                    Boolean updateQuantity = false;
                    Integer newQuantity = null;

                    while (true) {
                        System.out.print("Enter new quantity (leave blank to keep current quantity): ");
                        newQuantityStr = scanner.nextLine();

                        if (newQuantityStr.isEmpty()) {
                            break;
                        }
                        try {
                            newQuantity = Integer.parseInt(newQuantityStr);
                            updateQuantity = true;
                            break;
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid quantity format. Please try again.");
                        }
                    }
                    if (!updateQuantity && !updatePrice) {
                        System.out.println("There is nothing to update.");
                        break;
                    }

                    newInventory.updateDevice(name, updatePrice ? newPriceStr : null,
                            updateQuantity ? String.valueOf(newQuantity) : null);
                    break;
                }
                case 4: {
                    newInventory.ListAll();
                    break;
                }
                case 5: {
                    newInventory.findCheapest();
                    break;
                }
                case 6: {
                    newInventory.sortDevices();
                    break;
                }
                case 7: {
                    System.out.println("Total inventory value: $" + newInventory.calculateTotal());
                    break;
                }
                case 8: {
                    System.out.print("Enter the name of the device to restock: ");
                    name = scanner.nextLine();
                    System.out.print("Do you want to add or remove stock? (Add/Remove): ");
                    String restockOption = " ";
                    restockOption = scanner.nextLine();
                    Boolean addOrRemove = false;
                    if (restockOption.equals("Add")) {
                        System.out.print("Enter the quantity to add: ");
                        addOrRemove = true;
                    } else if (restockOption.equals("Remove")) {
                        System.out.print("Enter the quantity to remove: ");
                        addOrRemove = false;
                    } else {
                        System.out.println("Invalid option.");
                        break;
                    }
                    int restockQuantity = scanner.nextInt();
                    scanner.nextLine();
                    newInventory.restockDevice(name, restockQuantity, addOrRemove);
                    break;
                }
                case 9: {
                    newInventory.exportInventory();
                    break;
                }
                case 0: {
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                }
                default:
                    System.err.println("Invalid input. Please choose a number between 0-9.");
                    break;
            }
        }
        scanner.close();
    }

    private static boolean isValidPriceFormat(String price) {
        if (price.endsWith("$")) {
            try {
                Double.parseDouble(price.substring(0, price.length() - 1));
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return false;
    }
}
