import java.util.Scanner;
import java.io.File;

class driver {
    static int[] allIDs = new int[300];

    public static void main(String[] args) throws Exception {
        File file = new File("/home/mustafa/gtu/cse222/homeworks/hw1/content.txt");
        Scanner fileScanner = new Scanner(file);
        order[] orders = new order[100];
        operator[] operators = new operator[100];
        retail_customer[] RetCustomers = new retail_customer[100];
        corporate_customer[] CorCustomers = new corporate_customer[100];
        int IDcounter = 0;
        allIDs[0] = 0;
        int operatorCount = 0;
        int retCustomerCount = 0;
        int corCustomerCount = 0;
        int orderCount = 0;
        while (fileScanner.hasNextLine()) {

            String line = fileScanner.nextLine();
            String[] items = line.split(";");
            if (items[0].equals("order")) {
                if (validityCheckForOrder(line) == 1) {
                    orders[orderCount] = new order();
                    orders[orderCount].setProduct_name(items[1]);
                    orders[orderCount].setCount(Integer.parseInt(items[2]));
                    orders[orderCount].setTotal_price(Integer.parseInt(items[3]));
                    orders[orderCount].setStatus(Integer.parseInt(items[4]));
                    orders[orderCount].setCustomer_ID(Integer.parseInt(items[5]));
                    orderCount++;
                }

            } else if (items[0].equals("retail_customer")) {

                if (validityCheckForRCustomer(RetCustomers, operators, line) == 1) {
                    RetCustomers[retCustomerCount] = new retail_customer();
                    RetCustomers[retCustomerCount].setName(items[1]);
                    RetCustomers[retCustomerCount].setSurname(items[2]);
                    RetCustomers[retCustomerCount].setAdress(items[3]);
                    RetCustomers[retCustomerCount].setPhone(items[4]);
                    RetCustomers[retCustomerCount].setID(Integer.parseInt(items[5]));
                    RetCustomers[retCustomerCount].setOperator_ID(Integer.parseInt(items[6]));
                    allIDs[IDcounter] = Integer.parseInt(items[5]);
                    retCustomerCount++;
                }

            } else if (items[0].equals("corporate_customer")) {

                if (validityCheckForCCustomer(CorCustomers, operators, line) == 1) {
                    CorCustomers[corCustomerCount] = new corporate_customer();
                    CorCustomers[corCustomerCount].setName(items[1]);
                    CorCustomers[corCustomerCount].setSurname(items[2]);
                    CorCustomers[corCustomerCount].setAdress(items[3]);
                    CorCustomers[corCustomerCount].setPhone(items[4]);
                    CorCustomers[corCustomerCount].setID(Integer.parseInt(items[5]));
                    CorCustomers[corCustomerCount].setOperator_ID(Integer.parseInt(items[6]));
                    CorCustomers[corCustomerCount].setCompany_name(items[7]);
                    allIDs[IDcounter] = Integer.parseInt(items[5]);
                    corCustomerCount++;
                }

            } else if (items[0].equals("operator")) {

                if (validityCheckForOperator(operators, CorCustomers, RetCustomers, line) == 1) {
                    operators[operatorCount] = new operator();
                    operators[operatorCount].setName(items[1]);
                    operators[operatorCount].setSurname(items[2]);
                    operators[operatorCount].setAdress(items[3]);
                    operators[operatorCount].setPhone(items[4]);
                    operators[operatorCount].setID(Integer.parseInt(items[5]));
                    operators[operatorCount].setWage(Integer.parseInt(items[6]));
                    allIDs[IDcounter] = Integer.parseInt(items[5]);
                    operatorCount++;
                }

            }
        }
        fileScanner.close();
        for (int i = 0; i < corCustomerCount; ++i) {
            CorCustomers[i].define_orders(orders);
        }
        for (int i = 0; i < retCustomerCount; ++i) {
            RetCustomers[i].define_orders(orders);
        }
        for (int i = 0; i < operatorCount; ++i) {
            operators[i].define_customers(RetCustomers);
            operators[i].define_customers(CorCustomers);
        }

        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Please enter your ID...");
        int input = 0;
        try {
            input = inputScanner.nextInt();
        } catch (Exception exc) {
            System.out.println("Invalid input!");
            System.exit(1);
        }

        int flag1 = 0;
        int flag2 = 0;
        for (int i = 0; i < operatorCount; ++i) {
            if (input == operators[i].getID()) {
                flag1 = 1;
                flag2 = 1;
                System.out.println("*** Operator Screen ***");
                System.out.println("-----------------------");
                operators[i].print_operator();
            }
        }
        if (flag1 == 0) {
            for (int i = 0; i < corCustomerCount; ++i) {
                if (input == CorCustomers[i].getID()) {
                    flag2 = 1;
                    System.out.println("*** Customer Screen ***");
                    System.out.println("-----------------------");
                    CorCustomers[i].print_customer();
                }
            }
            for (int i = 0; i < retCustomerCount; ++i) {
                if (input == RetCustomers[i].getID()) {
                    flag2 = 1;
                    System.out.println("*** Customer Screen ***");
                    System.out.println("-----------------------");
                    RetCustomers[i].print_customer();
                }
            }
        }
        if (flag2 == 0) {
            System.err.println("No operator/customer was found with ID " + input + ". Please try again.");
            inputScanner.close();
        }
    }

    private static int validityCheckForOrder(String line) {
        // 1 = name, 2 = count , 3 = price, 4 = status, 5 = customerID
        String[] items = line.split(";");
        int count = 0;
        int price = 0;
        int status = 0;
        int customerID = 0;
        // check the integers
        try {
            count = Integer.parseInt(items[2]);
            price = Integer.parseInt(items[3]);
            status = Integer.parseInt(items[4]);
            customerID = Integer.parseInt(items[5]);
        } catch (Exception e) {
            return 0;
        }

        // check if the integers are not positive
        if (price < 0 || customerID <= 0 || count <= 0) {
            return 0;
        }
        // check the integers are too large
        else if (price > Integer.MAX_VALUE || customerID > Integer.MAX_VALUE) {
            return 0;
        }
        // check if the status is in the given range (0-3)
        else if (status > 3 || status < 0) {
            return 0;
        }
        // check if there is a extra or missing column
        // also check if there is any missing semicolon
        else if (items.length != 6) {
            return 0;
        }
        // check if string is empty
        for (String s : items) {
            if (s == "") {
                return 0;
            }
        }
        // check the strings' validity
        for (String item : items) {
            if (item.isBlank()) {
                return 0;
            }
        }
        return 1;
    }

    private static int validityCheckForRCustomer(retail_customer[] RetCustomers, operator[] operators,
            String line) {
        // 1 = name, 2 = surname , 3 = adress, 4 = phone, 5 = ID, 6 = operatorID
        String[] items = line.split(";");
        int ID = 0;
        int operatorID = 0;
        // check the integers
        try {
            ID = Integer.parseInt(items[5]);
            operatorID = Integer.parseInt(items[6]);
        } catch (Exception e) {
            return 0;
        }
        // check if there is same ID
        for (int i : allIDs) {
            if (i == ID) {
                return 0;
            }
        }

        // check if the integers are not positive
        if (ID <= 0 || operatorID <= 0) {
            return 0;
        }
        // check the integers are too large
        else if (ID > Integer.MAX_VALUE || operatorID > Integer.MAX_VALUE) {
            return 0;
        }
        // check if there is a extra or missing column
        // also check if there is any missing semicolon
        else if (items.length != 7) {
            return 0;
        }
        // check if string is empty
        for (String s : items) {
            if (s == "") {
                return 0;
            }
        }
        // check the strings' validity
        for (String item : items) {
            if (item.isBlank()) {
                return 0;
            }
        }
        return 1;
    }

    private static int validityCheckForCCustomer(corporate_customer[] CorCustomers, operator[] operators,
            String line) {
        // 1 = name, 2 = surname , 3 = adress, 4 = phone, 5 = ID, 6 = operatorID
        // 7 = companyName

        String[] items = line.split(";");
        int ID = 0;
        int operatorID = 0;
        // check the integers
        try {
            ID = Integer.parseInt(items[5]);
            operatorID = Integer.parseInt(items[6]);
        } catch (Exception e) {
            return 0;
        }
        // check if there is same ID
        for (int i : allIDs) {
            if (i == ID) {
                return 0;
            }
        }

        // check if the integers are not positive
        if (ID <= 0 || operatorID <= 0) {
            return 0;
        }
        // check the integers are too large
        else if (ID > Integer.MAX_VALUE || operatorID > Integer.MAX_VALUE) {
            return 0;
        }
        // check if there is a extra or missing column
        // also check if there is any missing semicolon
        else if (items.length != 8) {
            System.out.println("18");
            return 0;
        }
        // check if string is empty
        for (String s : items) {
            if (s == "") {
                return 0;
            }
        }
        // check the strings' validity
        for (String item : items) {
            if (item.isBlank()) {
                return 0;
            }
        }
        return 1;
    }

    private static int validityCheckForOperator(operator[] operators, corporate_customer[] cCustomers,
            retail_customer[] rCustomers, String line) {
        // 1 = name, 2 = surname , 3 = adress, 4 = phone, 5 = ID, 6 = wage
        String[] items = line.split(";");
        int ID = 0;
        int wage = 0;
        try {
            ID = Integer.parseInt(items[5]);
            wage = Integer.parseInt(items[6]);
        } catch (Exception e) {
            return 0;
        }
        // check if there is same ID
        for (int i : allIDs) {
            if (i == ID) {
                return 0;
            }
        }
        // check if the integers are not positive
        if (ID <= 0 || wage <= 0) {
            return 0;
        }
        // check the integers are too large
        else if (ID > Integer.MAX_VALUE || wage > Integer.MAX_VALUE) {
            return 0;
        }
        // check if there is a extra or missing column
        // also check if there is any missing semicolon
        else if (items.length != 7) {
            return 0;
        }
        // check if string is empty
        for (String s : items) {
            if (s == "") {
                return 0;
            }
        }
        // check the strings' validity
        for (String item : items) {
            if (item.isBlank()) {
                return 0;
            }
        }
        return 1;
    }
}