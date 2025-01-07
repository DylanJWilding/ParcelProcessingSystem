package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    // Singleton instance
    private static Log instance;

    // In-memory storage for log entries
    private StringBuilder logEntries;

    // Private constructor to enforce Singleton pattern
    private Log() {
        logEntries = new StringBuilder();
    }

    // Get the Singleton instance of Log
    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    // Add an entry to the log
    public void addEntry(String entry) {
        logEntries.append(entry).append("\n");
    }

    // Write log entries to a file
    public void writeToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(logEntries.toString());
        }
    }
}
