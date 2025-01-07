package controllers;

import models.Customer;
import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    // Constructor
    public QueueOfCustomers() {
        customerQueue = new LinkedList<>();
    }

    // Add a customer to the queue
    public void addToQueue(Customer customer) {
        customerQueue.add(customer);
        System.out.println("Customer added to queue: " + customer);
    }

    // Remove a customer from the queue
    public Customer removeFromQueue() {
        Customer removedCustomer = customerQueue.poll();
        if (removedCustomer != null) {
            System.out.println("Customer removed from queue: " + removedCustomer);
        } else {
            System.out.println("No customers in queue to remove.");
        }
        return removedCustomer;
    }

    // Get the next customer in the queue
    public Customer getNextInQueue() {
        Customer nextCustomer = customerQueue.peek();
        if (nextCustomer != null) {
            System.out.println("Next customer in queue: " + nextCustomer);
        } else {
            System.out.println("No customers in queue.");
        }
        return nextCustomer;
    }

    // Display all customers in the queue
    public void displayQueue() {
        if (customerQueue.isEmpty()) {
            System.out.println("No customers in queue.");
        } else {
            System.out.println("Customers in queue:");
            for (Customer customer : customerQueue) {
                System.out.println(customer);
            }
        }
    }
}
