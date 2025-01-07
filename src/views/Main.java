package views;

import models.Parcel;
import models.Customer;

public class Main {
    public static void main(String[] args) {
        // Test 1: Create and display a Parcel
        Parcel parcel1 = new Parcel("C101", "4x2x3", 10, 7, "Waiting");
        System.out.println("Parcel Details:");
        System.out.println(parcel1);

        // Test 2: Calculate Collection Fee
        float fee = parcel1.calculateCollectionFee();
        System.out.println("Collection Fee for Parcel " + parcel1.getParcelId() + ": $" + fee);

        // Test 3: Update Parcel Status
        parcel1.updateStatus("Collected");
        System.out.println("Updated Parcel Status:");
        System.out.println(parcel1);

        // Test 4: Create and display a Customer
        Customer customer1 = new Customer(1, "John Brown", "C101");
        System.out.println("\nCustomer Details:");
        System.out.println(customer1);

        // Test 5: Check Customer Queue Position
        int queuePosition = customer1.checkQueuePosition();
        System.out.println("Customer Queue Position: " + queuePosition);

        // Test 6: Update Customer Details
        customer1.setName("Jane Doe");
        customer1.setParcelId("C200");
        System.out.println("\nUpdated Customer Details:");
        System.out.println(customer1);
    }
}
