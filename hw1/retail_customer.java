
public class retail_customer extends customer {
    public void print_customer() {
        System.out.println("(a retail customer)");
        System.out.println("Name & Surname: " + getName() + " " + getSurname());
        System.out.println("Adress: " + getAdress());
        System.out.println("Phone: " + getPhone());
        System.out.println("ID: " + getID());
        System.out.println("Operator ID: " + getOperator_ID());
        print_orders();
    }
}
