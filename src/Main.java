import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

    private JFrame frame;
    private JTextField itemNameField;
    private JTextField costField;
    private JTextField quantityField;
    private JTextField totalSalesField;
    private JTextArea itemListArea;
    private JLabel errorLabel; // Added error label for UI-based error messages
    
    private SalesSlip salesSlip;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Main() {
        salesSlip = new SalesSlip();  // Initialize the SalesSlip object
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        // Labels and fields for item, cost, and quantity
        JLabel lblNewLabel = new JLabel("Item:");
        lblNewLabel.setBounds(143, 6, 61, 16);
        frame.getContentPane().add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Cost: $");
        lblNewLabel_1.setBounds(123, 34, 61, 16);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Quantity");
        lblNewLabel_2.setBounds(123, 67, 61, 16);
        frame.getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Total Sales:");
        lblNewLabel_3.setBounds(105, 245, 99, 16);
        frame.getContentPane().add(lblNewLabel_3);
        
        // Total sales field (read-only)
        totalSalesField = new JTextField();
        totalSalesField.setBounds(183, 240, 130, 26);
        totalSalesField.setEditable(false);  // Read-only
        frame.getContentPane().add(totalSalesField);
        totalSalesField.setColumns(10);
        
        // Input fields for item name, cost, and quantity
        itemNameField = new JTextField();
        itemNameField.setBounds(183, 1, 130, 26);
        frame.getContentPane().add(itemNameField);
        itemNameField.setColumns(10);
        
        costField = new JTextField();
        costField.setBounds(183, 29, 130, 26);
        frame.getContentPane().add(costField);
        costField.setColumns(10);
        
        quantityField = new JTextField();
        quantityField.setBounds(183, 62, 130, 26);
        frame.getContentPane().add(quantityField);
        quantityField.setColumns(10);
        
        // Add Item button
        JButton btnNewButton = new JButton("Add Item to the Sales List");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemToList();  // Add item when button is clicked
            }
        });
        btnNewButton.setBounds(102, 92, 246, 29);
        frame.getContentPane().add(btnNewButton);
        
        // Text area for the list of items
        itemListArea = new JTextArea();
        itemListArea.setBounds(71, 141, 277, 99);
        itemListArea.setEditable(false);  // Make it read-only
        frame.getContentPane().add(itemListArea);
        
        // Error label (for invalid input)
        errorLabel = new JLabel("");  // Initially empty
        errorLabel.setBounds(61, 115, 277, 16);  // Placed just below the button
        frame.getContentPane().add(errorLabel);
    }
    
    // Method to add an item to the list
    private void addItemToList() {
        try {
            // Clear any previous error message
            errorLabel.setText("");
            
            // Retrieve input from the fields
            String name = itemNameField.getText();
            double cost = Double.parseDouble(costField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            
            // Validate input: Cost should be < 100 and quantity should be > 0
            if (cost < 100 && quantity > 0) {
                // Create a SalesItem object and add it to the SalesSlip
                SalesItem item = new SalesItem(name, cost, quantity);
                salesSlip.addItem(item);

                // Update the item list and total sales in the GUI
                itemListArea.setText(salesSlip.toString());
                totalSalesField.setText(String.format("$%.2f", salesSlip.getTotalSales()));

                // Clear the input fields for the next entry
                itemNameField.setText("");
                costField.setText("");
                quantityField.setText("");
            } else {
                // If cost is >= 100 or quantity <= 0, show an error message
                errorLabel.setText("Invalid input: Cost must be < $100 and quantity > 0.");
            }
        } catch (NumberFormatException ex) {
            // Handle invalid number formats
            errorLabel.setText("Please enter valid numeric values for cost and quantity.");
        }
    }
}