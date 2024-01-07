import javax.swing.*;
import java.awt.event.*;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATMInterface extends JFrame implements ActionListener {
    private BankAccount account;

    private JTextField withdrawField, depositField, balanceField;
    private JButton withdrawButton, depositButton, balanceButton;

    public ATMInterface(BankAccount account) {
        this.account = account;

        withdrawField = new JTextField(10);
        depositField = new JTextField(10);
        balanceField = new JTextField(15);
        balanceField.setEditable(false);

        withdrawButton = new JButton("Withdraw");
        depositButton = new JButton("Deposit");
        balanceButton = new JButton("Check Balance");

        withdrawButton.addActionListener(this);
        depositButton.addActionListener(this);
        balanceButton.addActionListener(this);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Withdraw Amount:"));
        panel.add(withdrawField);
        panel.add(withdrawButton);
        panel.add(new JLabel("Deposit Amount:"));
        panel.add(depositField);
        panel.add(depositButton);
        panel.add(balanceButton);
        panel.add(new JLabel("Current Balance:"));
        panel.add(balanceField);

        add(panel);
        setTitle("ATM Interface");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            String withdrawText = withdrawField.getText();
            if (!withdrawText.isEmpty()) {
                double withdrawAmount = Double.parseDouble(withdrawText);
                if (account.withdraw(withdrawAmount)) {
                    JOptionPane.showMessageDialog(this, "Withdrawal successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient balance!");
                }
            }
        } else if (e.getSource() == depositButton) {
            String depositText = depositField.getText();
            if (!depositText.isEmpty()) {
                double depositAmount = Double.parseDouble(depositText);
                account.deposit(depositAmount);
                JOptionPane.showMessageDialog(this, "Deposit successful!");
            }
        } else if (e.getSource() == balanceButton) {
            double balance = account.getBalance();
            balanceField.setText(String.valueOf(balance));
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000); // Initial balance of 1000
        ATMInterface atmInterface = new ATMInterface(userAccount);
    }
}
