package br.com.azalim.calculator;

import br.com.azalim.calculator.buttons.*;
import br.com.azalim.calculator.buttons.operator.Operator;
import br.com.azalim.calculator.buttons.operator.OperatorButton;
import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.components.Display;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public final class Calculator extends JFrame {

    public static Calculator INSTANCE;

    private String firstNumber = "", secondNumber = "";

    private OperatorButton operatorButton;

    private final Display display;

    public Calculator() {

        super("Calculator");

        INSTANCE = this;

        loadIcon();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(235, 325);
        setResizable(false);

        getContentPane().setLayout(new GridBagLayout());
        getContentPane().setBackground(Constants.DARKEST_GRAY);

        display = addDisplay();
        addButtons();
        handleBackspace();

    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(Double firstNumber) {
        this.firstNumber = firstNumber == null ? "" : formatNumber(firstNumber);
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Double secondNumber) {
        this.secondNumber = secondNumber == null ? "" : formatNumber(secondNumber);
    }

    public void setCurrentNumber(Function<String, String> function) {

        if (operatorButton == null) {
            firstNumber = function.apply(firstNumber);
        } else {
            secondNumber = function.apply(secondNumber);
        }

        updateDisplay();

    }

    public Operator getOperator() {
        return Optional.ofNullable(operatorButton).map(OperatorButton::getOperator).orElse(null);
    }

    public void setOperatorButton(OperatorButton button) {

        operatorButton = button;

        if (button == null) {

            // TODO operatorButton.setBorder(null);

        } else {

            // TODO operatorButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));

        }

    }

    public String formatNumber(double number) {
        return Constants.DECIMAL_FORMAT.format(number);
    }

    public void calculate() {

        if (firstNumber.isBlank() || secondNumber.isBlank() || operatorButton == null) {
            return;
        }

        double firstNumber = Double.parseDouble(this.firstNumber);
        double secondNumber = Double.parseDouble(this.secondNumber);
        double calculatedNumber = operatorButton.getOperator().getCalculateFunction().apply(firstNumber, secondNumber);

        this.firstNumber = formatNumber(calculatedNumber);
        this.secondNumber = "";

        setOperatorButton(null);

        updateDisplay();

    }

    public void updateDisplay() {

        String label = Stream.of(secondNumber, firstNumber)
                .filter(number -> !number.isBlank())
                .findFirst().orElse("0");

        display.setText(label);

        Font currentFont = display.getFont();
        int stringWidth = display.getFontMetrics(currentFont).stringWidth(label);
        double widthRatio = (double) display.getWidth() / stringWidth;
        int newFontSize = Math.min((int) Math.round(currentFont.getSize() * widthRatio * 0.95), Constants.ORIGINAL_DISPLAY_FONT_SIZE);

        display.setFont(new Font(currentFont.getName(), Font.PLAIN, newFontSize));

    }

    private void loadIcon() {

        try {

            URL url = getClass().getResource("/icon.png");

            if (url == null) {
                throw new NullPointerException();
            }

            ImageIcon imageIcon = new ImageIcon(ImageIO.read(url));
            setIconImage(imageIcon.getImage());

            Taskbar taskbar = Taskbar.getTaskbar();
            taskbar.setIconImage(imageIcon.getImage());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Display addDisplay() {

        Display display = new Display();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        getContentPane().add(display, gridBagConstraints);

        return display;

    }

    private void addButtons() {

        int index = 0;

        for (Button button : new Button[]{
                new ClearButton(),
                new SignButton(),
                new PercentButton(),
                Operator.DIVISION.getButton(),
                new NumberButton(7, List.of(KeyEvent.VK_NUMPAD7, KeyEvent.VK_7)),
                new NumberButton(8, List.of(KeyEvent.VK_NUMPAD8, KeyEvent.VK_8)),
                new NumberButton(9, List.of(KeyEvent.VK_NUMPAD9, KeyEvent.VK_9)),
                Operator.MULTIPLICATION.getButton(),
                new NumberButton(4, List.of(KeyEvent.VK_NUMPAD4, KeyEvent.VK_4)),
                new NumberButton(5, List.of(KeyEvent.VK_NUMPAD5, KeyEvent.VK_5)),
                new NumberButton(6, List.of(KeyEvent.VK_NUMPAD6, KeyEvent.VK_6)),
                Operator.SUBTRACTION.getButton(),
                new NumberButton(1, List.of(KeyEvent.VK_NUMPAD1, KeyEvent.VK_1)),
                new NumberButton(2, List.of(KeyEvent.VK_NUMPAD2, KeyEvent.VK_2)),
                new NumberButton(3, List.of(KeyEvent.VK_NUMPAD3, KeyEvent.VK_3)),
                Operator.ADDITION.getButton(),
                new NumberButton(0, List.of(KeyEvent.VK_NUMPAD0, KeyEvent.VK_0)),
                new PointButton(),
                new EqualsButton()
        }) {

            int x = index % 4;
            int y = index / 4 + 1;
            index += button.gridWidth;

            GridBagConstraints gridBagConstraints = new GridBagConstraints();

            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints.gridwidth = button.gridWidth;
            gridBagConstraints.ipady = 20;
            gridBagConstraints.weightx = 1;
            gridBagConstraints.gridx = x;
            gridBagConstraints.gridy = y;
            gridBagConstraints.insets = new Insets(
                    y == 1 ? -1 : 1,
                    x == 0 ? -1 : 1,
                    y == 5 ? -1 : 1,
                    x == 3 ? -1 : 1
            );

            getContentPane().add(button, gridBagConstraints);

        }

    }

    private void handleBackspace() {

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                    setCurrentNumber(currentNumber -> currentNumber.isEmpty() ? currentNumber : currentNumber.substring(0, currentNumber.length() - 1));
                }
            }
        });

    }

}
