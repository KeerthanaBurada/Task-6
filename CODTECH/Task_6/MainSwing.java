import javax.swing.*;
import java.awt.*;

public class MainSwing {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(3, 1, 10, 10));

        JButton patientButton = new JButton("Manage Patients");
        JButton appointmentButton = new JButton("Manage Appointments");
        JButton doctorButton = new JButton("Manage Doctors");

        patientButton.addActionListener(e -> new PatientSwing());
        appointmentButton.addActionListener(e -> new AppointmentSwing());
        doctorButton.addActionListener(e -> new DoctorSwing());

        frame.add(patientButton);
        frame.add(appointmentButton);
        frame.add(doctorButton);

        frame.setVisible(true);
    }
}

