import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class StaffSwing {
    private static ArrayList<Staff> staffList = new ArrayList<>();

    public static void showStaffManager() {
        JFrame frame = new JFrame("Manage Staff");
        frame.setSize(400, 300);

        JTextField nameField = new JTextField();
        JTextField roleField = new JTextField();
        JTextField contactField = new JTextField();

        JButton addStaffButton = new JButton("Add Staff");
        JButton viewStaffButton = new JButton("View Staff");

        addStaffButton.addActionListener(e -> {
            String name = nameField.getText();
            String role = roleField.getText();
            String contact = contactField.getText();
            staffList.add(new Staff(name, role, contact));
            JOptionPane.showMessageDialog(frame, "Staff added successfully!");
        });

        viewStaffButton.addActionListener(e -> {
            StringBuilder staffDetails = new StringBuilder();
            for (Staff staff : staffList) {
                staffDetails.append(staff).append("\n");
            }
            JTextArea staffArea = new JTextArea(staffDetails.toString());
            JOptionPane.showMessageDialog(frame, new JScrollPane(staffArea), "Staff Details", JOptionPane.INFORMATION_MESSAGE);
        });

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Role:"));
        panel.add(roleField);
        panel.add(new JLabel("Contact:"));
        panel.add(contactField);
        panel.add(addStaffButton);
        panel.add(viewStaffButton);

        frame.add(panel);
        frame.setVisible(true);
    }
}

class Staff {
    private String name;
    private String role;
    private String contact;

    public Staff(String name, String role, String contact) {
        this.name = name;
        this.role = role;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Role: " + role + ", Contact: " + contact;
    }
}
