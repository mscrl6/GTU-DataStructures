
public class operator extends person {
    private customer[] customers = new customer[100];
    private int wage;
    private int customerCount = 0;

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public void print_operator() {
        System.out.println("Name & Surname: " + getName() + " " + getSurname());
        System.out.println("Adress: " + getAdress());
        System.out.println("Phone: " + getPhone());
        System.out.println("ID: " + getID());
        System.out.println("Wage: " + getWage());
        System.out.println("-----------------------");
        print_customers();
    }

    public void print_customers() {
        for (int i = 0; i < customerCount; ++i) {
            int counter = i + 1;
            System.out.print("Customer #" + counter + " ");
            customers[i].print_customer();
        }
    }

    public void define_customers(customer[] cus) {
        for (customer nextCustomer : cus) {
            if (nextCustomer == null) {
                continue;
            }
            if (customerCount >= customers.length) {
                return;
            }
            if (nextCustomer.getOperator_ID() == this.getID()) {
                customers[customerCount] = nextCustomer;
                customerCount++;
            }
        }
    }
}
