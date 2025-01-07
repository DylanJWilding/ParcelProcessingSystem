package views;

import controllers.ParcelMap;
import controllers.QueueOfCustomers;
import controllers.Worker;
import models.Customer;
import models.Parcel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize data structures
            ParcelMap parcelMap = new ParcelMap();
            QueueOfCustomers queueOfCustomers = new QueueOfCustomers();
            Worker worker = new Worker(queueOfCustomers, parcelMap);

            // Populate data
            parcelMap.addParcel(new Parcel("C101", "4x2x3", 10, 7));
            parcelMap.addParcel(new Parcel("C102", "5x3x2", 15, 4));
            queueOfCustomers.addToQueue(new Customer(1, "John Brown", "C101"));
            queueOfCustomers.addToQueue(new Customer(2, "Jane Smith", "C102"));

            // Launch GUI
            JFrame frame = new JFrame("Parcel Processing System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            // MainView includes all sub-views
            MainView mainView = new MainView(parcelMap, queueOfCustomers, worker);
            frame.add(mainView, BorderLayout.CENTER);

            frame.setSize(800, 600);
            frame.setVisible(true);
        });
    }
}
