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
        // Automatically write to file after each log entry
        writeToFile("output/logs.txt");
    }

    public void writeToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(logEntries.toString());
        } catch (IOException e) {
            System.err.println("Error writing logs to file: " + e.getMessage());
        }
    }
}
