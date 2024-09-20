import java.util.ArrayList;

public class SalesSlip {
    private ArrayList<SalesItem> itemList;

    // Constructor
    public SalesSlip() {
        itemList = new ArrayList<>();
    }

    // Add an item to the list
    public void addItem(SalesItem item) {
        itemList.add(item);
    }

    // Calculate the total sales from all items
    public double getTotalSales() {
        double total = 0;
        for (SalesItem item : itemList) {
            total += item.getTotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (SalesItem item : itemList) {
            result.append(item.toString()).append("\n");
        }
        return result.toString();
    }
}