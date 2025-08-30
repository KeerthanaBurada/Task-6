import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AppointmentSwing {
    JFrame frame;
    JTextField idField, patientIdField, doctorIdField, dateField;

    public AppointmentSwing() {
        frame = new JFrame("Appointment Module");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        frame.add(new JLabel("Appointment ID:"));
        idField = new JTextField();
        frame.add(idField);

        frame.add(new JLabel("Patient ID:"));
        patientIdField = new JTextField();
        frame.add(patientIdField);

        frame.add(new JLabel("Doctor ID:"));
        doctorIdField = new JTextField();
        frame.add(doctorIdField);

        frame.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        frame.add(dateField);

        JButton addButton = new JButton("Book Appointment");
        addButton.addActionListener(e -> addAppointment());
        frame.add(addButton);

        frame.setVisible(true);
    }

    private void addAppointment() {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement("INSERT INTO Appointment VALUES (?, ?, ?, ?)")) {

            ps.setInt(1, Integer.parseInt(idField.getText()));
            ps.setInt(2, Integer.parseInt(patientIdField.getText()));
            ps.setInt(3, Integer.parseInt(doctorIdField.getText()));
            ps.setDate(4, java.sql.Date.valueOf(dateField.getText()));

            ps.executeUpdate();
            JOptionPane.showMessageDialog(frame, "Appointment Booked Successfully!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }
}

