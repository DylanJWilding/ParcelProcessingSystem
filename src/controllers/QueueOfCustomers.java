package controllers;

import models.Customer;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> queue;

    // Constructor
    public QueueOfCustomers() {
        queue = new LinkedList<>();
    }

    // Add a customer to the queue
    public void addToQueue(Customer customer) {
        queue.add(customer);
    }

    // Remove a customer from the queue and return the removed customer
    public Customer removeFromQueue() {
        return queue.poll();
    }

    // Get the next customer without removing
    public Customer getNextCustomer() {
        return queue.peek(); // Retrieves the next customer without removing
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Get all customers as a list
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(queue); // Return a copy of the queue as a list
    }

    // Display all customers in the queue
    public void displayQueue() {
        System.out.println("Customers in the queue:");
        for (Customer customer : queue) {
            System.out.println(customer);
        }
    }
}
