
public class order {
    private String product_name;
    private int count;
    private int total_price;
    private int status;
    private int customer_ID;

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public int getCount() {
        return count;
    }

    public int getTotal_price() {
        return total_price;
    }

    public String getStatus() {
        if (status == 0) {
            return "Initialized";
        } else if (status == 1) {

            return "Processing";
        } else if (status == 2) {

            return "Completed";
        } else if (status == 3) {

            return "Cancelled";
        } else {
            return "";
        }
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void print_order() {
        System.out.println("Product name: " + product_name + " - "
                + "Count: " + count + " - " + "Total Price: "
                + total_price + " - " + "Status: " + getStatus());
    }
}
