// This code is of: jairogarciarincon :)
// Source: https://www.jairogarciarincon.com/clase/interfaces-de-usuario-con-java-swing/calculadora-guiada-en-java-swing

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Calculator extends JFrame {
    JLabel display;
    int numberOfButtons = 17;
    JButton[] buttons = new JButton[numberOfButtons];
    String[] textButtons = {"Result","7","8","9","/","4","5","6","*","1","2","3","-","C","0",".","+"};

    int[] xButtons = {15, 15, 80, 145, 210, 15, 80, 145, 210, 15, 80, 145, 210, 15, 80, 145, 210};
    int[] yButtons = {90, 155, 155, 155, 155, 220, 220, 220, 220, 285, 285, 285, 285, 350, 350, 350, 350};

    int[] numberButtons = {14, 9, 10, 11, 5, 6, 7, 1, 2, 3};

    int[] operationsButtons = {16, 12, 8, 4};

    int widhtButton = 50;
    int highButtons = 50;

    boolean newNumber = true;
    boolean decimalPoint = false;

    double operating1 = 0;
    double operating2 = 0;
    double result = 0;

    String operation = "";

    public Calculator() {
        initDisplay();
        initButtons();
        initWindow();
        numberEvent();
        decimalEvent();
        operationEvent();
        resultEvent();
        cleanEvent();
    }

    private void initDisplay() {
        display = new JLabel("0");
        display.setBounds(15, 15, 245, 60);
        display.setOpaque(true);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        display.setBorder(new LineBorder(Color.DARK_GRAY));
        display.setFont(new Font("MONOSPACED", Font.PLAIN, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display);
    }

    private void initButtons() {
        for (int i = 0; i < numberOfButtons; i++) {
            buttons[i] = new JButton(textButtons[i]);
            int size = (i == 0) ? 24 : 16;
            int widht = (i == 0) ? 245 : widhtButton;
            /** if (i == 0) {
                int widht = 245;
            } else {
                int widht = widhtButton;
            }
             **/
            buttons[i].setBounds(xButtons[i], yButtons[i], widht, highButtons);
            buttons[i].setFont(new Font("MONOSPACED", Font.PLAIN, size));
            buttons[i].setOpaque(true);
            buttons[i].setFocusPainted(false);
            buttons[i].setBackground(Color.DARK_GRAY);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setBorder(new LineBorder(Color.DARK_GRAY));
            add(buttons[i]);
        }
    }

    public void initWindow() {
        setLayout(null);
        setTitle("Calculator.");
        setSize(290, 455);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
    }

    private void numberEvent() {
        for (int i = 0; i < 10; i++) {
            int numButton = numberButtons[i];
            buttons[numButton].addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (newNumber) {
                        if (!textButtons[numButton].equals("0")) {
                            display.setText(textButtons[numButton]);
                            newNumber = false;
                        }
                    } else {
                        display.setText(display.getText() + textButtons[numButton]);
                    }
                }
            });
        }
    }

    private void decimalEvent() {
        buttons[15].addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!decimalPoint) {
                    display.setText(display.getText() + textButtons[15]);
                    decimalPoint = true;
                    newNumber = false;
                }
            }
        });
    }

    private void operationEvent() {
        for (int numButton : operationsButtons) {
            buttons[numButton].addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (operation.equals("")) {
                        operation = textButtons[numButton];
                        operating2 = Double.parseDouble(display.getText());

                        newNumber = true;
                        decimalPoint = false;
                    } else {
                        operating2 = result();
                        operation = textButtons[numButton];
                    }

                    System.out.println(operating2 + " " + operation + " " + operating1);
                }
            });
        }
    }

    private double result() {
        operating1 = Double.parseDouble(display.getText());

        switch (operation) {
            case "+": result = operating2 + operating1; break;
            case "-": result = operating2 - operating1; break;
            case "*": result = operating2 * operating1; break;
            case "/": result = operating2 / operating1; break;
        }

        Locale localeActual = Locale.getDefault();
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(localeActual);
        symbols.setDecimalSeparator('.');
        DecimalFormat resultFormat = new DecimalFormat("#.######", symbols);
        display.setText(String.valueOf(resultFormat.format(result)));

        clean();
        return result;
    }

    private void clean() {
        operating1 = operating2 = 0;
        operation = "";
        newNumber = true;
        decimalPoint = false;
    }

    private void resultEvent() {
        buttons[0].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                result();
            }
        });
    }

    private void cleanEvent() {
        buttons[13].addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText("0");
                clean();
            }
        });
    }

    public static void main(String[] args) {
        new Calculator();
    }
}