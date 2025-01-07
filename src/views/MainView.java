package views;

import controllers.ParcelMap;
import controllers.QueueOfCustomers;
import controllers.Worker;

import javax.swing.*;
import java.awt.*;

public class MainView extends JPanel {
    private ParcelView parcelView;
    private CustomerQueueView customerQueueView;
    private ProcessingAreaView processingAreaView;
    private JButton processNextButton;

    public MainView(ParcelMap parcelMap, QueueOfCustomers queueOfCustomers, Worker worker) {
        setLayout(new BorderLayout());

        // Create views
        parcelView = new ParcelView(parcelMap);
        customerQueueView = new CustomerQueueView(queueOfCustomers);
        processingAreaView = new ProcessingAreaView();

        // Create process next button
        processNextButton = new JButton("Process Next Customer");
        processNextButton.addActionListener(e -> {
            worker.processNextCustomer();
            customerQueueView.refresh(queueOfCustomers);
            parcelView.refresh(parcelMap);
        });

        // Add views to the panel
        add(parcelView, BorderLayout.WEST);
        add(customerQueueView, BorderLayout.EAST);
        add(processingAreaView, BorderLayout.CENTER);
        add(processNextButton, BorderLayout.SOUTH);
    }

    public ParcelView getParcelView() {
        return parcelView;
    }

    public CustomerQueueView getCustomerQueueView() {
        return customerQueueView;
    }

    public JButton getProcessNextButton() {
        return processNextButton;
    }

    public ProcessingAreaView getProcessingAreaView() {
        return processingAreaView;
    }
}
