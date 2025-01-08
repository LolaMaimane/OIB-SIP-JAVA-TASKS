/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Lola Maimane
 */
public class ATMInterfaceGUI {
  
  
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Account currentAccount;
    private Account account1;
    private Account account2;
    private JLabel welcomeLabel;  // Label to display the welcome message

    public ATMInterfaceGUI() {
        account1 = new Account("123456", "1234");
        account2 = new Account("654321", "5678");

        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        createLoginPanel();
        createMenuPanel();
        createTransactionHistoryPanel();
        createWithdrawPanel();
        createDepositPanel();
        createTransferPanel();

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void createLoginPanel() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));

        JLabel userIdLabel = new JLabel("User ID:");
        JTextField userIdField = new JTextField();
        JLabel pinLabel = new JLabel("PIN:");
        JPasswordField pinField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(e -> {
            String userId = userIdField.getText();
            String pin = new String(pinField.getPassword());

            if (account1.authenticate(userId, pin)) {
                currentAccount = account1;
                cardLayout.show(mainPanel, "Menu");
                welcomeLabel.setText("Welcome, Lola!");
            } else if (account2.authenticate(userId, pin)) {
                currentAccount = account2;
                cardLayout.show(mainPanel, "Menu");
                welcomeLabel.setText("Welcome, User!");
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid User ID or PIN", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginPanel.add(userIdLabel);
        loginPanel.add(userIdField);
        loginPanel.add(pinLabel);
        loginPanel.add(pinField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        mainPanel.add(loginPanel, "Login");
    }

    private void createMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(6, 1));

        // Add a label for the welcome message
        welcomeLabel = new JLabel("Welcome, Guest", JLabel.CENTER);
        menuPanel.add(welcomeLabel);

        JButton transactionHistoryButton = new JButton("Transaction History");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");
        JButton logoutButton = new JButton("Logout");

        transactionHistoryButton.addActionListener(e -> cardLayout.show(mainPanel, "TransactionHistory"));
        withdrawButton.addActionListener(e -> cardLayout.show(mainPanel, "Withdraw"));
        depositButton.addActionListener(e -> cardLayout.show(mainPanel, "Deposit"));
        transferButton.addActionListener(e -> cardLayout.show(mainPanel, "Transfer"));
        logoutButton.addActionListener(e -> cardLayout.show(mainPanel, "Login"));

        menuPanel.add(transactionHistoryButton);
        menuPanel.add(withdrawButton);
        menuPanel.add(depositButton);
        menuPanel.add(transferButton);
        menuPanel.add(logoutButton);

        mainPanel.add(menuPanel, "Menu");
    }

    private void createTransactionHistoryPanel() {
        JPanel transactionHistoryPanel = new JPanel(new BorderLayout());

        JTextArea historyArea = new JTextArea();
        historyArea.setEditable(false);
        JButton backButton = new JButton("Back");

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        transactionHistoryPanel.add(new JScrollPane(historyArea), BorderLayout.CENTER);
        transactionHistoryPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.add(transactionHistoryPanel, "TransactionHistory");

        transactionHistoryPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                historyArea.setText(String.join("\n", currentAccount.getTransactionHistory()));
            }
        });
    }

    private void createWithdrawPanel() {
        JPanel withdrawPanel = new JPanel(new GridLayout(3, 1));

        JTextField amountField = new JTextField();
        JButton withdrawButton = new JButton("Withdraw");
        JButton backButton = new JButton("Back");

        withdrawButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.withdraw(amount);
                JOptionPane.showMessageDialog(frame, "Withdrawal successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        withdrawPanel.add(amountField);
        withdrawPanel.add(withdrawButton);
        withdrawPanel.add(backButton);

        mainPanel.add(withdrawPanel, "Withdraw");
    }

    private void createDepositPanel() {
        JPanel depositPanel = new JPanel(new GridLayout(3, 1));

        JTextField amountField = new JTextField();
        JButton depositButton = new JButton("Deposit");
        JButton backButton = new JButton("Back");

        depositButton.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                JOptionPane.showMessageDialog(frame, "Deposit successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        depositPanel.add(amountField);
        depositPanel.add(depositButton);
        depositPanel.add(backButton);

        mainPanel.add(depositPanel, "Deposit");
    }

    private void createTransferPanel() {
        JPanel transferPanel = new JPanel(new GridLayout(4, 1));

        JTextField recipientField = new JTextField();
        JTextField amountField = new JTextField();
        JButton transferButton = new JButton("Transfer");
        JButton backButton = new JButton("Back");

        transferButton.addActionListener(e -> {
            String recipientId = recipientField.getText();
            Account recipient = recipientId.equals(account1.getUserId()) ? account1 :
                                recipientId.equals(account2.getUserId()) ? account2 : null;

            if (recipient != null && !recipient.equals(currentAccount)) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    currentAccount.transfer(recipient, amount);
                    JOptionPane.showMessageDialog(frame, "Transfer successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid recipient User ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Menu"));

        transferPanel.add(recipientField);
        transferPanel.add(amountField);
        transferPanel.add(transferButton);
        transferPanel.add(backButton);

        mainPanel.add(transferPanel, "Transfer");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new ATMInterfaceGUI());
    }    }

