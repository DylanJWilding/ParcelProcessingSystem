package views;

import javax.swing.*;
import java.awt.*;

public class ProcessingAreaView extends JPanel {
    private JLabel processingLabel;

    public ProcessingAreaView() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Processing Area"));

        // Initialize the label to display processing information
        processingLabel = new JLabel("No customer currently being processed.");
        processingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(processingLabel, BorderLayout.CENTER);
    }

    // Method to update the processing area with details
    public void updateProcessingArea(String details) {
        processingLabel.setText(details);
    }
}
