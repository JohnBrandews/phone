
    import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Test {

    // Global variables
    private static DefaultTableModel model;
    private static JTextField textName, textLastName, textPhNumber;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // JFrame setup
            JFrame frame = new JFrame("Contact Manager");
            frame.setSize(800, 400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Initialize the table model
            model = new DefaultTableModel(new Object[]{"Name", "Last Name", "PhNumber"}, 0);
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            // Initialize text fields
            textName = new JTextField();
            textLastName = new JTextField();
            textPhNumber = new JTextField();

            // Initialize buttons
            JButton buttonAdd = new JButton("Add");
            JButton buttonDelete = new JButton("Delete");
            JButton buttonUpdate = new JButton("Update");
            JButton buttonGenerateContacts = new JButton("Generate Contacts");

            // Set layout constraints for components
            setConstraints(frame, scrollPane, 0, 0, 780, 200);

            setConstraints(frame, new JLabel("Name:"), 20, 220, 100, 25);
            setConstraints(frame, new JLabel("Last Name:"), 20, 265, 100, 25);
            setConstraints(frame, new JLabel("PhNumber:"), 20, 310, 100, 25);

            setConstraints(frame, textName, 150, 220, 100, 25);
            setConstraints(frame, textLastName, 150, 265, 100, 25);
            setConstraints(frame, textPhNumber, 150, 310, 100, 25);

            setConstraints(frame, buttonAdd, 280, 220, 100, 25);
            setConstraints(frame, buttonDelete, 280, 265, 100, 25);
            setConstraints(frame, buttonUpdate, 410, 220, 100, 25);
            setConstraints(frame, buttonGenerateContacts, 410, 265, 150, 25);

            // Set button colors
            Color redColor = new Color(255, 0, 0);
            buttonDelete.setForeground(Color.WHITE);
            buttonDelete.setBackground(redColor);

            // Add action listeners to buttons
            buttonAdd.addActionListener(e -> addContact(textName.getText(), textLastName.getText(), textPhNumber.getText()));
            buttonDelete.addActionListener(e -> deleteSelectedRow(table));
            buttonUpdate.addActionListener(e -> updateSelectedRow(table, textName.getText(), textLastName.getText(), textPhNumber.getText()));
            buttonGenerateContacts.addActionListener(e -> generateAndDisplayContacts());

            // Set layout and make frame visible
            frame.setLayout(null);
            frame.setVisible(true);
        });
    }

    // Method to set layout constraints for components
    private static void setConstraints(Container container, Component component, int x, int y, int width, int height) {
        component.setBounds(x, y, width, height);
        container.add(component);
    }

    // Method to add a new contact to the table
    private static void addContact(String name, String lastName, String phNumber) {
        model.addRow(new Object[]{name, lastName, phNumber});
        clearTextFields();
    }

    // Method to delete the selected row from the table
    private static void deleteSelectedRow(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(null, "Select a row to delete.");
        }
    }

    // Method to update the selected row in the table
    private static void updateSelectedRow(JTable table, String updatedName, String updatedLastName, String updatedPhNumber) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            model.setValueAt(updatedName, selectedRow, 0);
            model.setValueAt(updatedLastName, selectedRow, 1);
            model.setValueAt(updatedPhNumber, selectedRow, 2);
        } else {
            JOptionPane.showMessageDialog(null, "Select a row to update.");
        }
    }

    // Method to generate and display all contacts in a dialog box
    private static void generateAndDisplayContacts() {
        StringBuilder contacts = new StringBuilder("All Contacts:\n");
        for (int i = 0; i < model.getRowCount(); i++) {
            contacts.append("Name: ").append(model.getValueAt(i, 0))
                    .append(", Last Name: ").append(model.getValueAt(i, 1))
                    .append(", PhNumber: ").append(model.getValueAt(i, 2)).append("\n");
        }
        JOptionPane.showMessageDialog(null, contacts.toString());
    }

    // Method to clear text fields
    private static void clearTextFields() {
        JTextField[] textFields = {textName, textLastName, textPhNumber};
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }
}

