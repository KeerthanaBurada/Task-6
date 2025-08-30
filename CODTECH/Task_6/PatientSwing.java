import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class PatientSwing {
    JFrame frame;
    JTextField idField, nameField, ageField;

    public PatientSwing() {
        frame = new JFrame("Patient Module");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        frame.add(new JLabel("Patient ID:"));
        idField = new JTextField();
        frame.add(idField);

        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Age:"));
        ageField = new JTextField();
        frame.add(ageField);

        JButton addButton = new JButton("Add Patient");
        addButton.addActionListener(e -> addPatient());
        frame.add(addButton);

        frame.setVisible(true);
    }

    private void addPatient() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Patient VALUES (?, ?, ?)")) {

            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, nameField.getText());
            ps.setInt(3, Integer.parseInt(ageField.getText()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Patient Added Successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }
}

