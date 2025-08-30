import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DoctorSwing {
    JFrame frame;
    JTextField idField, nameField, specializationField;

    public DoctorSwing() {
        frame = new JFrame("Doctor Module");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        frame.add(new JLabel("Doctor ID:"));
        idField = new JTextField();
        frame.add(idField);

        frame.add(new JLabel("Name:"));
        nameField = new JTextField();
        frame.add(nameField);

        frame.add(new JLabel("Specialization:"));
        specializationField = new JTextField();
        frame.add(specializationField);

        JButton addButton = new JButton("Add Doctor");
        addButton.addActionListener(e -> addDoctor());
        frame.add(addButton);

        frame.setVisible(true);
    }

    private void addDoctor() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Doctor VALUES (?, ?, ?)")) {

            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setString(2, nameField.getText());
            ps.setString(3, specializationField.getText());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Doctor Added Successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }
}
