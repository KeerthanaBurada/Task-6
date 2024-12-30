import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainSwing {
    public static void main(String[] args) {
        // Create the main frame
        JFrame frame = new JFrame("Hospital Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        // Create buttons for each module
        JButton patientButton = new JButton("Manage Patients");
        JButton appointmentButton = new JButton("Schedule Appointment");
        JButton ehrButton = new JButton("View EHR");
        JButton billingButton = new JButton("Manage Billing");
        JButton inventoryButton = new JButton("Manage Inventory");
        JButton staffButton = new JButton("Manage Staff");

        // Set button actions
        patientButton.addActionListener((ActionEvent e) -> PatientSwing.showPatientManagement());
        appointmentButton.addActionListener((ActionEvent e) -> AppointmentSwing.showAppointmentScheduler());
        ehrButton.addActionListener((ActionEvent e) -> PatientSwing.showEHRViewer());
        billingButton.addActionListener((ActionEvent e) -> BillingSwing.showBillingManager());
        inventoryButton.addActionListener((ActionEvent e) -> MedicalSupplySwing.showInventoryManager());
        staffButton.addActionListener((ActionEvent e) -> StaffSwing.showStaffManager());

        // Layout for buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.add(patientButton);
        panel.add(appointmentButton);
        panel.add(ehrButton);
        panel.add(billingButton);
        panel.add(inventoryButton);
        panel.add(staffButton);

        // Add panel to frame and display
        frame.add(panel);
        frame.setVisible(true);
    }
}
