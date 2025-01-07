package views;

import controllers.QueueOfCustomers;

import javax.swing.*;
import java.awt.*;

public class CustomerQueueView extends JPanel {
    private DefaultListModel<String> customerListModel;
    private JList<String> customerList;

    public CustomerQueueView(QueueOfCustomers queueOfCustomers) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Customer Queue"));

        customerListModel = new DefaultListModel<>();
        customerList = new JList<>(customerListModel);
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

    public String getSelectedCustomer() {
        return customerList.getSelectedValue();
    }
}
