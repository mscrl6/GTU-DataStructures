
public class customer extends person {
    private int operator_ID;
    private order[] orders = new order[100];
    private int orderCount = 0;

    public int getOperator_ID() {
        return operator_ID;
    }

    public void setOperator_ID(int operator_ID) {
        this.operator_ID = operator_ID;
    }

    public void print_customer() {
    }

    public void print_orders() {
        for (int i = 0; i < orderCount; ++i) {
            int counter = i + 1;
            System.out.print("Order #" + counter + " => ");
            orders[i].print_order();
        }
        System.out.println("-----------------------");
    }

    public void define_orders(order[] or) {
        for (order nextOrder : or) {
            if (nextOrder == null) {
                continue;
            }
            if (orderCount >= orders.length) {
                return;
            }
            if (nextOrder.getCustomer_ID() == getID()) {
                orders[orderCount] = nextOrder;
                orderCount++;
            }

        }
    }

}
