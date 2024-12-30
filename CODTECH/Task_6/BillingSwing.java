import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class BillingSwing {
    private static ArrayList<Bill> billList = new ArrayList<>();

    public static void showBillingManager() {
        JFrame frame = new JFrame("Manage Billing");
        frame.setSize(400, 300);

        JTextField patientNameField = new JTextField();
        JTextField itemField = new JTextField();
        JTextField costField = new JTextField();

        JButton addItemButton = new JButton("Add Item");
        JButton finalizeButton = new JButton("Finalize Bill");

        ArrayList<String> items = new ArrayList<>();
        ArrayList<Double> costs = new ArrayList<>();

        addItemButton.addActionListener(e -> {
            String item = itemField.getText();
            double cost = Double.parseDouble(costField.getText());
            items.add(item);
            costs.add(cost);
            itemField.setText("");
            costField.setText("");
            JOptionPane.showMessageDialog(frame, "Item added!");
        });

        finalizeButton.addActionListener(e -> {
            String patientName = patientNameField.getText();
            Bill bill = new Bill(patientName, items, costs);
            billList.add(bill);
            JOptionPane.showMessageDialog(frame, "Bill finalized!");
            frame.dispose();
        });

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Patient Name:"));
        panel.add(patientNameField);
        panel.add(new JLabel("Item Description:"));
        panel.add(itemField);
        panel.add(new JLabel("Cost:"));
        panel.add(costField);
        panel.add(addItemButton);
        panel.add(finalizeButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void viewBills() {
        JFrame frame = new JFrame("View Bills");
        frame.setSize(400, 300);

        StringBuilder bills = new StringBuilder();
        for (Bill bill : billList) {
            bills.append(bill).append("\n");
        }

        JTextArea billArea = new JTextArea(bills.toString());
        JScrollPane scrollPane = new JScrollPane(billArea);

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}

class Bill {
    private String patientName;
    private ArrayList<String> items;
    private ArrayList<Double> costs;

    public Bill(String patientName, ArrayList<String> items, ArrayList<Double> costs) {
        this.patientName = patientName;
        this.items = items;
        this.costs = costs;
    }

    @Override
    public String toString() {
        StringBuilder billDetails = new StringBuilder("Patient: " + patientName + "\n");
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            billDetails.append(items.get(i)).append(": $").append(costs.get(i)).append("\n");
            total += costs.get(i);
        }
        billDetails.append("Total: $").append(total);
        return billDetails.toString();
    }
}
