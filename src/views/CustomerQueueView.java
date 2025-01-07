package views;

import javax.swing.*;
import java.awt.*;

public class CustomerQueueView extends JPanel {

    public CustomerQueueView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Customer Queue"));

        // Create a list to display the customer queue
        DefaultListModel<String> customerQueueModel = new DefaultListModel<>();
        JList<String> customerQueueList = new JList<>(customerQueueModel);

        // Add some sample data
        customerQueueModel.addElement("Customer 1: John Brown");
        customerQueueModel.addElement("Customer 2: Jane Smith");

        // Add the list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(customerQueueList);
        add(scrollPane, BorderLayout.CENTER);

        // Add a process button
        JButton processButton = new JButton("Process Next Customer");
        add(processButton, BorderLayout.SOUTH);

        // Action listener for the process button (to be implemented later)
        processButton.addActionListener(e -> {
            if (!customerQueueModel.isEmpty()) {
                customerQueueModel.remove(0); // Remove the first customer
                JOptionPane.showMessageDialog(this, "Processed the next customer!");
            } else {
                JOptionPane.showMessageDialog(this, "No customers in the queue!");
            }
        });
    }
}
