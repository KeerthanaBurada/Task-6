import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class AppointmentSwing {
    private static ArrayList<Appointment> appointmentList = new ArrayList<>();

    public static void showAppointmentScheduler() {
        JFrame frame = new JFrame("Schedule Appointments");
        frame.setSize(400, 300);

        JTextField patientNameField = new JTextField();
        JTextField doctorNameField = new JTextField();
        JTextField dateField = new JTextField();

        JButton saveButton = new JButton("Save Appointment");
        saveButton.addActionListener(e -> {
            String patientName = patientNameField.getText();
            String doctorName = doctorNameField.getText();
            String date = dateField.getText();
            appointmentList.add(new Appointment(patientName, doctorName, date));
            JOptionPane.showMessageDialog(frame, "Appointment saved successfully!");
            frame.dispose();
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Patient Name:"));
        panel.add(patientNameField);
        panel.add(new JLabel("Doctor Name:"));
        panel.add(doctorNameField);
        panel.add(new JLabel("Date (dd-mm-yyyy):"));
        panel.add(dateField);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void viewAppointments() {
        JFrame frame = new JFrame("View Appointments");
        frame.setSize(400, 300);

        StringBuilder appointments = new StringBuilder();
        for (Appointment appointment : appointmentList) {
            appointments.append(appointment).append("\n");
        }

        JTextArea appointmentArea = new JTextArea(appointments.toString());
        JScrollPane scrollPane = new JScrollPane(appointmentArea);

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}

class Appointment {
    private String patientName;
    private String doctorName;
    private String date;

    public Appointment(String patientName, String doctorName, String date) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Patient: " + patientName + ", Doctor: " + doctorName + ", Date: " + date;
    }
}
