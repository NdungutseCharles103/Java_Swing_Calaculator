package com.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {
    private JTextField displayField;
    private String currentInput = "";
    private double result = 0;
    private String lastOperator = "";

    public CalculatorApp() {
        setTitle("Calculator");
        setIconImage(new ImageIcon("icon.png").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setPreferredSize(new Dimension(300, 50));

        JButton button0 = new JButton("0");
        JButton button1 = new JButton("1");
        JButton button2 = new JButton("2");
        JButton button3 = new JButton("3");
        JButton button4 = new JButton("4");
        JButton button5 = new JButton("5");
        JButton button6 = new JButton("6");
        JButton button7 = new JButton("7");
        JButton button8 = new JButton("8");
        JButton button9 = new JButton("9");
        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JButton buttonMultiply = new JButton("*");
        JButton buttonDivide = new JButton("/");
        JButton buttonEquals = new JButton("=");
        JButton buttonClear = new JButton("C");

        button0.addActionListener(new NumberButtonListener("0"));
        button1.addActionListener(new NumberButtonListener("1"));
        button2.addActionListener(new NumberButtonListener("2"));
        button3.addActionListener(new NumberButtonListener("3"));
        button4.addActionListener(new NumberButtonListener("4"));
        button5.addActionListener(new NumberButtonListener("5"));
        button6.addActionListener(new NumberButtonListener("6"));
        button7.addActionListener(new NumberButtonListener("7"));
        button8.addActionListener(new NumberButtonListener("8"));
        button9.addActionListener(new NumberButtonListener("9"));
        buttonPlus.addActionListener(new OperatorButtonListener("+"));
        buttonMinus.addActionListener(new OperatorButtonListener("-"));
        buttonMultiply.addActionListener(new OperatorButtonListener("*"));
        buttonDivide.addActionListener(new OperatorButtonListener("/"));
        buttonEquals.addActionListener(new EqualsButtonListener());
        buttonClear.addActionListener(e -> {
            currentInput = "";
            result = 0;
            lastOperator = "";
            displayField.setText("");
        });

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        injectButton(button4, button7, button8, button9, button6, buttonDivide, button5, buttonMultiply, buttonPanel);
        injectButton(button0, button1, button2, button3, buttonPlus, buttonMinus, buttonEquals, buttonClear, buttonPanel);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(displayField, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
    }

    private void injectButton(JButton button0, JButton button1, JButton button2, JButton button3, JButton buttonPlus, JButton buttonMinus, JButton buttonEquals, JButton buttonClear, JPanel buttonPanel) {
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        buttonPanel.add(buttonMinus);
        buttonPanel.add(button0);
        buttonPanel.add(buttonEquals);
        buttonPanel.add(buttonPlus);
        buttonPanel.add(buttonClear);
    }

    private class NumberButtonListener implements ActionListener {
        private String number;

        public NumberButtonListener(String number) {
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            currentInput += number;
            displayField.setText(currentInput);
        }
    }

    private class OperatorButtonListener implements ActionListener {
        private String operator;

        public OperatorButtonListener(String operator) {
            this.operator = operator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (lastOperator.equals("")) {
                result = Double.parseDouble(currentInput);
            } else {
                result = calculate(result, Double.parseDouble(currentInput), lastOperator);
            }
            currentInput = "";
            lastOperator = operator;
            displayField.setText(String.valueOf(result));
        }
    }

    private class EqualsButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            result = calculate(result, Double.parseDouble(currentInput), lastOperator);
            currentInput = "";
            lastOperator = "";
            displayField.setText(String.valueOf(result));
        }
    }

    private double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        ImageIcon image = new ImageIcon("./src/images/icon.png");
        app.setIconImage(image.getImage());
        app.setVisible(true);
    }

}




