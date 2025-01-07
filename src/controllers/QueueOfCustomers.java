package controllers;

import models.Customer;

import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    public QueueOfCustomers() {
        customerQueue = new LinkedList<>();
    }

    public void addToQueue(Customer customer) {
        customerQueue.add(customer);
    }

    public Customer getNextInQueue() {
        return customerQueue.poll(); // Removes and returns the next customer
    }

    public Queue<Customer> getQueue() {
        return customerQueue; // Provides access to the queue
    }

    public void removeCustomer() {
        customerQueue.poll(); // Removes the next customer without returning it
    }
}
