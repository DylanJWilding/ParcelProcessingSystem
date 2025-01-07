package controllers;

import models.Customer;
import models.Parcel;

public class Worker {
    private final QueueOfCustomers customerQueue;
    private final ParcelMap parcelMap;

    public Worker(QueueOfCustomers customerQueue, ParcelMap parcelMap) {
        this.customerQueue = customerQueue;
        this.parcelMap = parcelMap;
    }

    public void processNextCustomer() {
        // Get the next customer from the queue
        Customer customer = customerQueue.getNextInQueue();
        if (customer == null) {
            Log.getInstance().addEntry("No customers to process.");
            System.out.println("No customers to process.");
            return;
        }

        // Find the parcel associated with the customer
        Parcel parcel = parcelMap.findParcelById(customer.getParcelId());
        if (parcel == null) {
            Log.getInstance().addEntry("Parcel not found for customer: " + customer.getName());
            System.out.println("Parcel not found for customer: " + customer.getName());
            return;
        }

        // Calculate the collection fee
        double fee = parcel.calculateCollectionFee();
        Log.getInstance().addEntry("Processing Customer: " + customer);
        Log.getInstance().addEntry("Parcel Details: " + parcel);
        Log.getInstance().addEntry("Collection Fee: $" + fee);

        System.out.println("Processing Customer: " + customer);
        System.out.println("Parcel Details: " + parcel);
        System.out.println("Collection Fee: $" + fee);

        // Update parcel status
        parcel.updateStatus("Collected");
        Log.getInstance().addEntry("Parcel status updated to: Collected");

        // Remove the customer from the queue
        customerQueue.removeCustomer();
        Log.getInstance().addEntry("Customer removed from queue: " + customer);
    }
}
