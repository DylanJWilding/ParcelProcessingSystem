package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {
    private static Log instance;
    private StringBuilder logEntries;

    private Log() {
        logEntries = new StringBuilder();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEntry(String entry) {
        logEntries.append(entry).append("\n");
    }

    public void writeToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(logEntries.toString());
            System.out.println("Logs saved to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing logs to file: " + e.getMessage());
        }
    }
}
