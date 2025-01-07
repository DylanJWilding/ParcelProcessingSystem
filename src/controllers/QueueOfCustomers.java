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
        customerQueue.add(customer);
        Log.getInstance().addEntry("Customer added to queue: " + customer);
    }

    public Customer getNextInQueue() {
        return customerQueue.peek();
    }

    public void removeCustomer() {
        Customer removedCustomer = customerQueue.poll();
        if (removedCustomer != null) {
            Log.getInstance().addEntry("Customer removed from queue: " + removedCustomer);
        }
    }

    public Iterable<Customer> getAllCustomers() {
        return customerQueue;
    }
}
