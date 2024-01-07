import javax.swing.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JLabel baseLabel, targetLabel, amountLabel, resultLabel;
    private JTextField amountField;
    private JComboBox<String> baseCurrencyBox, targetCurrencyBox;
    private JButton convertButton;

    // This method simulates fetching exchange rates from an API
    private double getExchangeRate(String baseCurrency, String targetCurrency) {
        // This is a placeholder for fetching real exchange rates from an API
        // In a real scenario, this method would call an API with appropriate endpoints and keys
        // For demonstration purposes, using a simplified example with fixed exchange rates
        // Replace this with your API integration logic
        if (baseCurrency.equals("USD") && targetCurrency.equals("EUR")) {
            return 0.85; // Example exchange rate for USD to EUR
        } else if (baseCurrency.equals("EUR") && targetCurrency.equals("USD")) {
            return 1.18; // Example exchange rate for EUR to USD
        } else {
            return 1.0; // Default rate if no match found
        }
    }

    public CurrencyConverter() {
        baseLabel = new JLabel("Base Currency:");
        targetLabel = new JLabel("Target Currency:");
        amountLabel = new JLabel("Amount:");
        resultLabel = new JLabel("");

        String[] currencies = {"USD", "EUR", "GBP", "JPY"}; // Example currencies
        baseCurrencyBox = new JComboBox<>(currencies);
        targetCurrencyBox = new JComboBox<>(currencies);

        amountField = new JTextField(10);
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        setLayout(null);

        baseLabel.setBounds(30, 30, 100, 30);
        baseCurrencyBox.setBounds(150, 30, 100, 30);
        targetLabel.setBounds(30, 70, 100, 30);
        targetCurrencyBox.setBounds(150, 70, 100, 30);
        amountLabel.setBounds(30, 110, 100, 30);
        amountField.setBounds(150, 110, 100, 30);
        convertButton.setBounds(100, 150, 100, 30);
        resultLabel.setBounds(30, 190, 250, 30);

        add(baseLabel);
        add(baseCurrencyBox);
        add(targetLabel);
        add(targetCurrencyBox);
        add(amountLabel);
        add(amountField);
        add(convertButton);
        add(resultLabel);

        setTitle("Currency Converter");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            String baseCurrency = (String) baseCurrencyBox.getSelectedItem();
            String targetCurrency = (String) targetCurrencyBox.getSelectedItem();
            String amountText = amountField.getText();

            if (!amountText.isEmpty()) {
                try {
                    double amount = Double.parseDouble(amountText);
                    double exchangeRate = getExchangeRate(baseCurrency, targetCurrency);
                    double convertedAmount = amount * exchangeRate;
                    resultLabel.setText(String.format("Converted Amount: %.2f %s", convertedAmount, targetCurrency));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter an amount to convert.");
            }
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}

