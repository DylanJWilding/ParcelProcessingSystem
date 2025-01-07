package controllers;

import models.Customer;
import models.Parcel;
import java.io.IOException;

public class Manager {
    public static void main(String[] args) {
        QueueOfCustomers customerQueue = new QueueOfCustomers();
        ParcelMap parcelMap = new ParcelMap();

        // Add customers to the queue
        customerQueue.addToQueue(new Customer(1, "John Brown", "C101"));
        customerQueue.addToQueue(new Customer(2, "Jane Smith", "C102"));

        // Add parcels to the map
        parcelMap.addParcel(new Parcel("C101", "4x2x3", 10, 7));
        parcelMap.addParcel(new Parcel("C102", "5x3x2", 15, 4));

        // Initialize Worker
        Worker worker = new Worker(customerQueue, parcelMap);

        // Process customers
        worker.processNextCustomer(); // Processes Customer 1
        worker.processNextCustomer(); // Processes Customer 2

        // Write logs to file
        try {
            Log.getInstance().writeToFile("output/logs.txt");
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }
}
