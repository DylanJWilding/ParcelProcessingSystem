package views;

import controllers.Worker;
import controllers.ParcelMap;
import controllers.QueueOfCustomers;
import models.Parcel;
import models.Customer;

public class Main {
    public static void main(String[] args) {
        // Test Worker Class
        System.out.println("\nTesting Worker:");

        // Create ParcelMap and QueueOfCustomers
        ParcelMap parcelMap = new ParcelMap();
        QueueOfCustomers queue = new QueueOfCustomers();

        // Add parcels to ParcelMap
        parcelMap.addParcel(new Parcel("C101", "4x2x3", 10, 7));
        parcelMap.addParcel(new Parcel("C102", "5x3x2", 15, 4));

        // Add customers to QueueOfCustomers
        queue.addToQueue(new Customer(1, "John Brown", "C101"));
        queue.addToQueue(new Customer(2, "Jane Smith", "C102"));

        // Create Worker and process customers
        Worker worker = new Worker(queue, parcelMap);
        worker.processNextCustomer();
        worker.processNextCustomer();
        worker.processNextCustomer(); // Test empty queue
    }
}
