package views;

import controllers.QueueOfCustomers;
import controllers.Worker;
import models.Customer;

import javax.swing.*;
import java.awt.*;

public class CustomerQueueView extends JPanel {
    private DefaultListModel<String> customerQueueModel;

    public CustomerQueueView(QueueOfCustomers queueOfCustomers, Worker worker) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Customer Queue"));

        // Create a list to display the customer queue
        customerQueueModel = new DefaultListModel<>();
        JList<String> customerQueueList = new JList<>(customerQueueModel);

        // Populate the list with customers from QueueOfCustomers
        for (Customer customer : queueOfCustomers.getAllCustomers()) {
            customerQueueModel.addElement(customer.toString());
        }

        // Add the list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(customerQueueList);
        add(scrollPane, BorderLayout.CENTER);

        // Add a process button
        JButton processButton = new JButton("Process Next Customer");
        add(processButton, BorderLayout.SOUTH);

        // Action listener for the process button
        processButton.addActionListener(e -> {
            if (!queueOfCustomers.isEmpty()) {
                Customer nextCustomer = queueOfCustomers.getNextCustomer();
                worker.processNextCustomer();
                JOptionPane.showMessageDialog(this, "Processed: " + nextCustomer);
                refresh(queueOfCustomers); // Refresh the queue view
            } else {
                JOptionPane.showMessageDialog(this, "No customers in the queue!");
            }
        });
    }

    // Method to refresh the customer queue list
    public void refresh(QueueOfCustomers queueOfCustomers) {
        customerQueueModel.clear();
        for (Customer customer : queueOfCustomers.getAllCustomers()) {
            customerQueueModel.addElement(customer.toString());
        }
    }
}
