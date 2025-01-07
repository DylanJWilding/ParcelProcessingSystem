package views;

import controllers.QueueOfCustomers;
import controllers.ParcelMap;
import controllers.Worker;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private ParcelView parcelView;
    private CustomerQueueView customerQueueView;

    public MainView(ParcelMap parcelMap, QueueOfCustomers queueOfCustomers, Worker worker) {
        setTitle("Parcel Processing System");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create views
        parcelView = new ParcelView(parcelMap);
        customerQueueView = new CustomerQueueView(queueOfCustomers, worker);

        // Add views to the frame
        add(parcelView, BorderLayout.WEST);
        add(customerQueueView, BorderLayout.EAST);

        // Add the single "Process Next Customer" button
        JButton processButton = new JButton("Process Next Customer");
        processButton.addActionListener(e -> {
            worker.processNextCustomer();
            customerQueueView.refresh(queueOfCustomers);
            parcelView.refresh(parcelMap);
        });

        add(processButton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
