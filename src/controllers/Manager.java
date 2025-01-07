package controllers;

import models.Customer;
import models.Parcel;
import views.MainView;

import javax.swing.*;
import java.awt.*;

public class Manager {
    public static void main(String[] args) {
        // Initialize data structures
        ParcelMap parcelMap = new ParcelMap();
        QueueOfCustomers queueOfCustomers = new QueueOfCustomers();

        // Populate parcels
        parcelMap.addParcel(new Parcel("C101", "4x2x3", 10, 7));
        parcelMap.addParcel(new Parcel("C102", "5x3x2", 15, 4));
        parcelMap.addParcel(new Parcel("C103", "3x3x3", 20, 10));

        // Populate customers
        queueOfCustomers.addToQueue(new Customer(1, "John Brown", "C101"));
        queueOfCustomers.addToQueue(new Customer(2, "Jane Smith", "C102"));
        queueOfCustomers.addToQueue(new Customer(3, "Michael Johnson", "C103"));

        // Create Worker
        Worker worker = new Worker(queueOfCustomers, parcelMap);

        // Launch GUI
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Parcel Processing System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            MainView mainView = new MainView(parcelMap, queueOfCustomers, worker);
            frame.add(mainView, BorderLayout.CENTER);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
