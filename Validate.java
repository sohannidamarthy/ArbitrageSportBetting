package ArbitrageOpportunitesGUI;

import javax.swing.*;
import java.awt.*;

public class Validate {
    public Validate() {
        JFrame frame = new JFrame("Arbitrage Opportunity Checker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 280);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Labels and input spinners
        JLabel label1 = new JLabel("Team 1 (American Odds):");
        JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));

        JLabel label2 = new JLabel("Team 2 (American Odds):");
        JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(0, -1000, 1000, 1));

        JButton button = new JButton("Validate");

        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Layout placement
        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        frame.add(label1, gbc);
        gbc.gridx = 1;
        frame.add(spinner1, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        frame.add(label2, gbc);
        gbc.gridx = 1;
        frame.add(spinner2, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        frame.add(button, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        frame.add(scrollPane, gbc);

        // Action listener for the button
        button.addActionListener(e -> {
            int val1 = (Integer) spinner1.getValue();
            int val2 = (Integer) spinner2.getValue();
            double prob1 = impliedProbability(val1);
            double prob2 = impliedProbability(val2);
            double totalProb = prob1 + prob2;

            StringBuilder result = new StringBuilder();
            result.append(String.format("Implied Probability 1: %.4f%n", prob1));
            result.append(String.format("Implied Probability 2: %.4f%n", prob2));
            result.append(String.format("Total Implied Probability: %.4f%n", totalProb));

            if (totalProb < 1.0) {
                result.append("✅ Arbitrage opportunity detected!");
            } else {
                result.append("❌ No arbitrage opportunity.");
            }

            resultArea.setText(result.toString());
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Helper method for converting American odds to implied probability
    public static double impliedProbability(int odds) {
        if (odds > 0) {
            return 100.0 / (odds + 100);
        } else {
            return -odds / (double) (-odds + 100);
        }
    }
}