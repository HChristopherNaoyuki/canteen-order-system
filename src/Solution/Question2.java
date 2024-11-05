package Solution;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Question2
{
    // GUI components
    private JFrame frame;
    private JList<String> menuList;
    private JTextArea orderSummary;
    private JTextField specialRequestField;
    private JCheckBox waterCheckbox;
    private ButtonGroup drinkGroup;
    private JButton placeOrderButton;
    private JButton calculateAmountButton;
    private JButton saveOrderButton;
    private JButton printOrderButton;
    private JButton exitButton;

    // List to store ordered items and total amount
    private List<String> orderItems;
    private double totalAmount;

    // Fixed prices for each menu item
    private final double CHICKEN_SALAD_PRICE = 20.0;
    private final double CLUB_SANDWICH_PRICE = 17.0;
    private final double BURGER_PRICE = 28.0;
    private final double VEGETABLE_WRAP_PRICE = 12.0;
    private final double PIE_PRICE = 15.0;
    private final double MILKSHAKE_PRICE = 17.0;
    private final double FRESH_JUICE_PRICE = 7.0;
    private final double COLD_DRINK_PRICE = 12.0;
    private final double TEA_PRICE = 10.0;
    private final double COFFEE_PRICE = 10.0;

    public Question2()
    {
        frame = new JFrame("Canteen Order System");

        // Apply Apple-like font
        Font appleFont = new Font("San Francisco", Font.PLAIN, 12);

        // Menu options for food items
        String[] menuOptions = {"Chicken Salad", "Club Sandwich", "Burger", "Vegetable Wrap", "Pie"};
        menuList = new JList<>(menuOptions);
        menuList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        menuList.setFont(appleFont);
        menuList.setBackground(Color.WHITE);
        menuList.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        menuList.setSelectionBackground(new Color(0xA6E1FF)); // Light blue for selection

        // Drink options as radio buttons
        JRadioButton milkshakeButton = new JRadioButton("Milkshake");
        JRadioButton juiceButton = new JRadioButton("Fresh Juice");
        JRadioButton coldDrinkButton = new JRadioButton("CoolDrink");
        JRadioButton teaButton = new JRadioButton("Tea");
        JRadioButton coffeeButton = new JRadioButton("Coffee");

        // Add drink buttons to the button group
        drinkGroup = new ButtonGroup();
        drinkGroup.add(milkshakeButton);
        drinkGroup.add(juiceButton);
        drinkGroup.add(coldDrinkButton);
        drinkGroup.add(teaButton);
        drinkGroup.add(coffeeButton);

        // Set the font for the radio buttons
        milkshakeButton.setFont(appleFont);
        juiceButton.setFont(appleFont);
        coldDrinkButton.setFont(appleFont);
        teaButton.setFont(appleFont);
        coffeeButton.setFont(appleFont);

        // Special requests and water checkbox
        specialRequestField = new JTextField(10);
        specialRequestField.setFont(appleFont);
        specialRequestField.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        waterCheckbox = new JCheckBox("Water");
        waterCheckbox.setFont(appleFont);

        // Order summary display area
        orderSummary = new JTextArea(8, 25);
        orderSummary.setEditable(false);
        orderSummary.setFont(appleFont);
        orderSummary.setBackground(Color.WHITE);
        orderSummary.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

        // Buttons for actions
        placeOrderButton = createAppleButton("Place Order");
        calculateAmountButton = createAppleButton("Amount Due");
        saveOrderButton = createAppleButton("Save Order");
        printOrderButton = createAppleButton("Print Order");
        exitButton = createAppleButton("Exit");

        // Add action listeners
        placeOrderButton.addActionListener(e -> placeOrder());
        calculateAmountButton.addActionListener(e -> calculateAmount());
        saveOrderButton.addActionListener(e -> saveOrder());
        printOrderButton.addActionListener(e -> printOrder());
        exitButton.addActionListener(e -> System.exit(0));

        // Layout and adding components to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 5, 5)); // Reduced grid spacing
        panel.setBackground(new Color(0xF4F4F4)); // Light gray background

        panel.add(new JLabel("Menu Items:"));
        panel.add(new JScrollPane(menuList));
        panel.add(new JLabel("Special Request:"));
        panel.add(specialRequestField);
        panel.add(new JLabel("Drink Options:"));

        JPanel drinkPanel = new JPanel();
        drinkPanel.setLayout(new GridLayout(0, 1, 5, 5)); // Reduced spacing for drink options
        drinkPanel.add(milkshakeButton);
        drinkPanel.add(juiceButton);
        drinkPanel.add(coldDrinkButton);
        drinkPanel.add(teaButton);
        drinkPanel.add(coffeeButton);

        panel.add(drinkPanel);
        panel.add(new JLabel("Additional:"));
        panel.add(waterCheckbox);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0xF4F4F4));
        buttonPanel.add(placeOrderButton);
        buttonPanel.add(calculateAmountButton);
        buttonPanel.add(saveOrderButton);
        buttonPanel.add(printOrderButton);
        buttonPanel.add(exitButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(new JScrollPane(orderSummary), BorderLayout.EAST);

        // Updated frame size to 512x512
        frame.setSize(512, 512); // New frame size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JButton createAppleButton(String text)
    {
        JButton button = new JButton(text);
        button.setFont(new Font("San Francisco", Font.PLAIN, 12));
        button.setBackground(new Color(0x007AFF)); // Apple blue
        button.setForeground(Color.WHITE);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Reduced padding
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(100, 35)); // Reduced button size

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x0051C1)); // Darker blue on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0x007AFF)); // Reset to original color
            }
        });

        return button;
    }

    private void placeOrder()
    {
        String menuChoice = menuList.getSelectedValue();
        if (menuChoice == null)
        {
            JOptionPane.showMessageDialog(frame, "Please select a menu item.");
            return;
        }

        String drinkChoice = null;
        for (AbstractButton button : java.util.Collections.list(drinkGroup.getElements()))
        {
            if (button.isSelected())
            {
                drinkChoice = button.getText();
                break;
            }
        }

        if (drinkChoice == null)
        {
            JOptionPane.showMessageDialog(frame, "Please select a drink.");
            return;
        }

        String specialRequest = specialRequestField.getText();
        boolean water = waterCheckbox.isSelected();

        orderSummary.append("Menu Choice: " + menuChoice + "\n");
        orderSummary.append("Special Request: " + (specialRequest.isEmpty() ? "None" : specialRequest) + "\n");
        orderSummary.append("Drink: " + drinkChoice + "\n");
        orderSummary.append("Water: " + (water ? "Yes" : "No") + "\n\n");
    }

    private void calculateAmount()
    {
        totalAmount = 0.0;
        String menuChoice = menuList.getSelectedValue();

        if (menuChoice != null)
        {
            switch (menuChoice)
            {
                case "Chicken Salad": totalAmount += CHICKEN_SALAD_PRICE; break;
                case "Club Sandwich": totalAmount += CLUB_SANDWICH_PRICE; break;
                case "Burger": totalAmount += BURGER_PRICE; break;
                case "Vegetable Wrap": totalAmount += VEGETABLE_WRAP_PRICE; break;
                case "Pie": totalAmount += PIE_PRICE; break;
            }
        }

        for (AbstractButton button : java.util.Collections.list(drinkGroup.getElements()))
        {
            if (button.isSelected())
            {
                switch (button.getText())
                {
                    case "Milkshake": totalAmount += MILKSHAKE_PRICE; break;
                    case "Fresh Juice": totalAmount += FRESH_JUICE_PRICE; break;
                    case "CoolDrink": totalAmount += COLD_DRINK_PRICE; break;
                    case "Tea": totalAmount += TEA_PRICE; break;
                    case "Coffee": totalAmount += COFFEE_PRICE; break;
                }
            }
        }

        orderSummary.append("Amount Due: R " + totalAmount + "\n\n");
    }

    private void printOrder()
    {
        JOptionPane.showMessageDialog(frame, new JScrollPane(new JTextArea(orderSummary.getText())), "Order Summary", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveOrder()
    {
        try (FileWriter writer = new FileWriter("NewOrder.txt"))
        {
            writer.write(orderSummary.getText());
            JOptionPane.showMessageDialog(frame, "Order saved successfully!");
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(frame, "Error saving order: " + ex.getMessage());
        }
    }

    public static void main(String[] args)
    {
        new Question2();
    }
}
