package controllers;

import models.Customer;
import models.Parcel;

public class Worker {
    private QueueOfCustomers customerQueue;
    private ParcelMap parcelMap;

    public Worker(QueueOfCustomers queueOfCustomers, ParcelMap parcelMap) {
        this.customerQueue = queueOfCustomers;
        this.parcelMap = parcelMap;
    }

    public void processNextCustomer() {
        // Get the next customer
        Customer customer = customerQueue.getNextInQueue();
        if (customer == null) {
            System.out.println("No customers to process.");
            return;
        }

        // Find the parcel associated with the customer
        Parcel parcel = parcelMap.findParcelById(customer.getParcelId());
        if (parcel == null) {
            System.out.println("Parcel not found for customer: " + customer.getName());
            return;
        }

        // Calculate the collection fee
        double fee = parcel.calculateCollectionFee();
        System.out.println("Processing Customer: " + customer);
        System.out.println("Parcel Details: " + parcel);
        System.out.println("Collection Fee: $" + fee);

        // Update parcel status to "Collected"
        parcel.updateStatus("Collected");
        System.out.println("Parcel status updated to: " + parcel.getStatus());

        // Remove customer from the queue
        customerQueue.removeFromQueue();
        System.out.println("Customer removed from queue: " + customer);
    }
}
