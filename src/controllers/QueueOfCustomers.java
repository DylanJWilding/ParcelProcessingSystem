package controllers;

import models.Customer;
import java.util.LinkedList;
import java.util.Queue;

public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    public QueueOfCustomers() {
        this.customerQueue = new LinkedList<>();
    }

    public void addToQueue(Customer customer) {
        customerQueue.offer(customer); // Adds customer to the queue
        System.out.println("Customer added to queue: " + customer);
    }

    public Customer getNextInQueue() {
        if (customerQueue.isEmpty()) {
            return null;
        }
        return customerQueue.poll(); // Retrieves and removes the head of the queue
    }

    public void removeFromQueue() {
        if (!customerQueue.isEmpty()) {
            customerQueue.poll();
        }
    }

    public Queue<Customer> getAllCustomers() {
        return new LinkedList<>(customerQueue); // Return a copy of the queue
    }

    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }
}
