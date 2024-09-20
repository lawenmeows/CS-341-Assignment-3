public class SalesItem {
    private String name;
    private double price;
    private int quantity;

    // Constructor
    public SalesItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Calculate the total for this sales item
    public double getTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " x " + quantity + " = $" + getTotal();
    }
}