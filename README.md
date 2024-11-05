# Canteen Order System

A simple Java-based GUI application for ordering food and drinks in a canteen. This system allows users to select menu items, drinks, and special requests, calculate the total amount due, and print or save the order.

## Features

- **Menu Selection**: Choose from a list of food items such as Chicken Salad, Club Sandwich, Burger, Vegetable Wrap, and Pie.
- **Drink Options**: Select from a variety of drinks including Milkshake, Fresh Juice, Cool Drink, Tea, and Coffee.
- **Special Requests**: Add special requests (optional) for personalized orders.
- **Additional Options**: Option to include water with the order.
- **Order Summary**: Display the selected items, drinks, special requests, and the total amount.
- **Save and Print**: Save the order to a text file or print the order summary.
- **Simple and Interactive**: Built using Java Swing for an easy-to-use interface.

## Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/HChristopherNaoyuki/canteen-order-system.git
   ```

2. Open the project in your favorite IDE (e.g., NetBeans, IntelliJ IDEA).

3. Ensure you have **Java Development Kit (JDK)** installed on your machine (version 8 or higher).

4. Build and run the project. The application will launch a window where you can interact with the system.

## Usage

1. **Select a Menu Item**: Choose one food item from the list.
2. **Pick a Drink**: Select a drink from the options (only one drink can be chosen).
3. **Special Requests**: Add any additional special requests if needed (optional).
4. **Water Option**: You can choose to include water with your order.
5. **Place Order**: Click the *Place Order* button to confirm your order.
6. **Calculate Total**: Click *Amount Due* to see the total cost of your order.
7. **Save Order**: Save your order to a file with the *Save Order* button.
8. **Print Order**: Print the order summary using the *Print Order* button.
9. **Exit**: Close the application by clicking the *Exit* button.

## Example

After selecting your items and clicking "Calculate Amount Due", the order summary will be displayed in the right-hand panel, showing:

```
Menu Choice: Chicken Salad
Special Request: None
Drink: Milkshake
Water: Yes

Amount Due: R 37.0
```

## Technologies Used

- **Java**: Core programming language.
- **Java Swing**: GUI framework for building the user interface.
- **File I/O**: Used for saving the order to a file.

---
