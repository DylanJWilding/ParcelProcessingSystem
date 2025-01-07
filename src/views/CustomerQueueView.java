package views;

import controllers.QueueOfCustomers;
import models.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class CustomerQueueView extends JPanel {
    private DefaultListModel<String> customerListModel;
    private JTextField searchField;

    public CustomerQueueView(QueueOfCustomers queueOfCustomers) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Customer Queue"));

        // Create search field
        searchField = new JTextField();
        searchField.setToolTipText("Search customers by name or parcel ID...");
        searchField.addActionListener(e -> filterQueue(queueOfCustomers)); // Trigger filter on Enter key

        // Create customer list
        customerListModel = new DefaultListModel<>();
        JList<String> customerList = new JList<>(customerListModel);

        // Add components
        add(searchField, BorderLayout.NORTH);
        add(new JScrollPane(customerList), BorderLayout.CENTER);

        // Populate initial data
        refresh(queueOfCustomers);
    }

    public void refresh(QueueOfCustomers queueOfCustomers) {
        customerListModel.clear();
        queueOfCustomers.getQueue().forEach(customer ->
                customerListModel.addElement(customer.toString())
        );
    }

    private void filterQueue(QueueOfCustomers queueOfCustomers) {
        String searchText = searchField.getText().toLowerCase();
        customerListModel.clear();
        queueOfCustomers.getQueue().stream()
                .filter(customer -> customer.getName().toLowerCase().contains(searchText) ||
                        customer.getParcelId().toLowerCase().contains(searchText))
                .collect(Collectors.toList())
                .forEach(customer -> customerListModel.addElement(customer.toString()));
    }
}
