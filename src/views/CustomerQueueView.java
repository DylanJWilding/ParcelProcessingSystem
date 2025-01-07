package views;

import controllers.QueueOfCustomers;
import controllers.Worker;

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
        add(new JScrollPane(customerQueueList), BorderLayout.CENTER);

        // Refresh the customer queue list initially
        refresh(queueOfCustomers);
    }

    // Method to refresh the customer queue list
    public void refresh(QueueOfCustomers queueOfCustomers) {
        customerQueueModel.clear();
        for (var customer : queueOfCustomers.getAllCustomers()) {
            customerQueueModel.addElement(customer.toString());
        }
    }
}
