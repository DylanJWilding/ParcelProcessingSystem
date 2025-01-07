package controllers;

import models.Customer;
import models.Parcel;
import views.MainView;

import javax.swing.*;
import java.awt.*;

public class Manager {
    public static void launch() {
        SwingUtilities.invokeLater(() -> {
            // Initialize data structures
            ParcelMap parcelMap = new ParcelMap();
            QueueOfCustomers customerQueue = new QueueOfCustomers();

            // Add some test data
            customerQueue.addToQueue(new Customer(1, "John Brown", "C101"));
            customerQueue.addToQueue(new Customer(2, "Jane Smith", "C102"));

            parcelMap.addParcel(new Parcel("C101", "4x2x3", 10, 7));
            parcelMap.addParcel(new Parcel("C102", "5x3x2", 15, 4));

            // Initialize MainView and Worker
            MainView mainView = new MainView(parcelMap, customerQueue, null); // Worker set later
            Worker worker = new Worker(customerQueue, parcelMap, mainView.getProcessingAreaView());

            // Assign the worker back to MainView
            mainView.getProcessNextButton().addActionListener(e -> {
                worker.processNextCustomer(); // Worker processes next customer
                mainView.getCustomerQueueView().refresh(customerQueue); // Refresh the customer queue
                mainView.getParcelView().refresh(parcelMap); // Refresh the parcel list
            });

            // Launch GUI
            JFrame frame = new JFrame("Parcel Processing System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            frame.add(mainView, BorderLayout.CENTER);
            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
