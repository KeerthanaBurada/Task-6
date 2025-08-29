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
            itemField.setText(""); costField.setText("");
            JOptionPane.showMessageDialog(frame, "Item added!");
        });

        finalizeButton.addActionListener(e -> {
            int patientId = Integer.parseInt(patientIdField.getText());

            // ====== SQL INSERT ======
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO Bill(patient_id, items, costs) VALUES(?,

