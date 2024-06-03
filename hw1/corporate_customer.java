
public class corporate_customer extends customer {
    private String company_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void print_customer() {
        System.out.println("(a corporate customer)");
        System.out.println("Name & Surname: " + getName() + " " + getSurname());
        System.out.println("Adress: " + getAdress());
        System.out.println("Phone: " + getPhone());
        System.out.println("ID: " + getID());
        System.out.println("Operator ID: " + getOperator_ID());
        System.out.println("Company Name: " + getCompany_name());
        print_orders();
    }
    
}
