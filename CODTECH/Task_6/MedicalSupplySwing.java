import javax.swing.*;
import java.awt.GridLayout;
import java.util.HashMap;

public class MedicalSupplySwing {
    private static HashMap<String, Integer> inventory = new HashMap<>();

    public static void showInventoryManager() {
        JFrame frame = new JFrame("Manage Inventory");
        frame.setSize(400, 300);

        JTextField itemNameField = new JTextField();
        JTextField quantityField = new JTextField();

        JButton addItemButton = new JButton("Add Item");
        JButton updateStockButton = new JButton("Update Stock");
        JButton viewInventoryButton = new JButton("View Inventory");

        addItemButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            int quantity = Integer.parseInt(quantityField.getText());
            inventory.put(itemName, quantity);
            JOptionPane.showMessageDialog(frame, "Item added successfully!");
        });

        updateStockButton.addActionListener(e -> {
            String itemName = itemNameField.getText();
            if (inventory.containsKey(itemName)) {
                int quantity = Integer.parseInt(quantityField.getText());
                inventory.put(itemName, quantity);
                JOptionPane.showMessageDialog(frame, "Stock updated!");
                if (quantity < 10) {
                    JOptionPane.showMessageDialog(frame, "Warning: Low stock for " + itemName);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Item not found.");
            }
        });

        viewInventoryButton.addActionListener(e -> {
            StringBuilder inventoryDetails = new StringBuilder();
            for (String item : inventory.keySet()) {
                inventoryDetails.append(item).append(": ").append(inventory.get(item)).append("\n");
            }
            JTextArea inventoryArea = new JTextArea(inventoryDetails.toString());
            JOptionPane.showMessageDialog(frame, new JScrollPane(inventoryArea), "Inventory", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Item Name:"));
        panel.add(itemNameField);
        panel.add(new JLabel("Quantity:"));
        panel.add(quantityField);
        panel.add(addItemButton);
        panel.add(updateStockButton);
        panel.add(viewInventoryButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}
