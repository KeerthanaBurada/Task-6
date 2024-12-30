import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;

public class PatientSwing {
    private static ArrayList<Patient> patientList = new ArrayList<>();

    public static void showPatientManagement() {
        JFrame frame = new JFrame("Manage Patients");
        frame.setSize(400, 300);

        JButton addPatientButton = new JButton("Add Patient");
        JButton viewPatientsButton = new JButton("View Patients");

        addPatientButton.addActionListener(e -> addPatient());
        viewPatientsButton.addActionListener(e -> viewPatients());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(addPatientButton);
        panel.add(viewPatientsButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    public static void showEHRViewer() {
        JFrame frame = new JFrame("View EHR");
        frame.setSize(400, 300);

        StringBuilder ehr = new StringBuilder();
        for (Patient patient : patientList) {
            ehr.append(patient).append("\n");
        }

        JTextArea ehrArea = new JTextArea(ehr.toString());
        JScrollPane scrollPane = new JScrollPane(ehrArea);

        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private static void addPatient() {
        JFrame frame = new JFrame("Add Patient");
        frame.setSize(400, 300);

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField contactField = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String gender = genderField.getText();
            String contact = contactField.getText();
            patientList.add(new Patient(name, age, gender, contact));
            JOptionPane.showMessageDialog(frame, "Patient added successfully!");
            frame.dispose();
        });

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Gender:"));
        panel.add(genderField);
        panel.add(new JLabel("Contact:"));
        panel.add(contactField);
        panel.add(saveButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void viewPatients() {
        JFrame frame = new JFrame("View Patients");
        frame.setSize(400, 300);

        StringBuilder patientData = new StringBuilder();
        for (Patient patient : patientList) {
            patientData.append(patient).append("\n");
        }

        JTextArea textArea = new JTextArea(patientData.toString());
        JScrollPane scrollPane = new JScrollPane(textArea);

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}

class Patient {
    private String name;
    private int age;
    private String gender;
    private String contact;

    public Patient(String name, int age, String gender, String contact) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Contact: " + contact;
    }
}
