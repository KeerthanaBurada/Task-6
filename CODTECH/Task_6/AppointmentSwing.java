import javax.swing.*;
import java.awt.GridLayout;
import java.sql.*;
import java.util.ArrayList;

public class AppointmentSwing {
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();

    public static void showAppointmentScheduler() {
        JFrame frame = new JFrame("Schedule Appointments");
        frame.setSize(400, 300);

        JTextField patientIdField = new JTextField();
        JTextField doctorIdField = new JTextField();
        JTextField dateField = new JTextField();

        JButton saveButton = new JButton("Save Appointment");
        saveButton.addActionListener(e -> {
            int patientId = Integer.parseInt(patientIdField.getText());
            int doctorId = Integer.parseInt(doctorIdField.getText());
            String date = dateField.getText();

            // ====== SQL INSERT ======
            try (Connection con = DBConnection.getConnection()) {
                String sql = "INSERT INTO Appointment(patient_id, doctor_id, date) VALUES(?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, patientId);
                ps.setInt(2, doctorId);
                ps.setString(3, date);
                ps.executeUpdate();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            appointmentList.add(new Appointment(patientId, doctorId, date));
            JOptionPane.showMessageDialog(frame, "Appointment saved successfully!");
            frame.dispose();
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Patient ID:")); panel.add(patientIdField);
        panel.add(new JLabel("Doctor ID:")); panel.add(doctorIdField);
        panel.add(new JLabel("Date (dd-mm-yyyy):")); panel.add(dateField);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void viewAppointments() {
        JFrame frame = new JFrame("View Appointments");
        frame.setSize(400, 300);

        // ====== SQL SELECT with JOIN ======
        StringBuilder appointments = new StringBuilder();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT a.appointment_id, p.name AS patient, d.name AS doctor, a.date " +
                         "FROM Appointment a " +
                         "JOIN Patient p ON a.patient_id = p.patient_id " +
                         "JOIN Staff d ON a.doctor_id = d.staff_id"; // assuming doctor stored in Staff
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                appointments.append("ID: ").append(rs.getInt("appointment_id"))
                        .append(", Patient: ").append(rs.getString("patient"))
                        .append(", Doctor: ").append(rs.getString("doctor"))
                        .append(", Date: ").append(rs.getString("date"))
                        .append("\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JTextArea appointmentArea = new JTextArea(appointments.toString());
        JScrollPane scrollPane = new JScrollPane(appointmentArea);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
}

class Appointment {
    private int patientId;
    private int doctorId;
    private String date;

    public Appointment(int patientId, int doctorId, String date) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
    }

    @Override
    public String toString() {
        return "PatientID: " + patientId + ", DoctorID: " + doctorId + ", Date: " + date;
    }
}

