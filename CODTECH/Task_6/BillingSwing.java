import javax.swing.*;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;

public class BillingSwing {
    private static ArrayList<Bill> billList = new ArrayList<>();

    public static void showBillingManager() {
        JFrame frame = new JFrame("Manage Billing");
        frame.setSize(400, 300);

        JTextField patientIdField = new JTextField();
        JTextField itemField = new JTextField();
        JTextField costField = new JTextField();

        JButton addItemButton = new JButton("Add Item");
        JButton finalizeButton = new JButton("Finalize Bill");

        ArrayList<String> items = new ArrayList<>();
        ArrayList<Double> costs = new ArrayList<>();

        addItemButton.addActionListener(e -> {
            items.add(itemField.getText());
            costs.add(Double.parseDouble(costField.getText()));
            itemField.setText("");
            costField.setText("");
            JOptionPane.showMessageDialog(frame, "Item added!");
        });

        finalizeButton.addActionListener(e -> {
            int patientId = Integer.parseInt(patientIdField.getText());

            // ====== SQL INSERT ======
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO Bill(patient_id, items, costs) VALUES(?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, patientId);
                ps.setString(2, String.join(",", items));
                ps.setString(3, costs.toString());
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            billList.add(new Bill(patientId, new ArrayList<>(items), new ArrayList<>(costs)));
            JOptionPane.showMessageDialog(frame, "Bill finalized successfully!");
            frame.dispose();
        });

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Patient ID:")); panel.add(patientIdField);
        panel.add(new JLabel("Item Name:")); panel.add(itemField);
        panel.add(new JLabel("Cost:")); panel.add(costField);
        panel.add(addItemButton); panel.add(finalizeButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}

class Bill {
    private int patientId;
    private ArrayList<String> items;
    private ArrayList<Double> costs;

    public Bill(int patientId, ArrayList<String> items, ArrayList<Double> costs) {
        this.patientId = patientId;
        this.items = items;
        this.costs = costs;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Patient ID: ").append(patientId).append("\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i)).append(" - ").append(costs.get(i)).append("\n");
        }
        return sb.toString();
    }
}

