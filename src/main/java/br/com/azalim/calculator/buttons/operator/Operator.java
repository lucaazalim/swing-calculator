package br.com.azalim.calculator.buttons.operator;

import java.awt.event.KeyEvent;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public enum Operator {

    ADDITION(
            "+",
            Double::sum,
            e -> (e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_EQUALS) || e.getKeyCode() == KeyEvent.VK_ADD || e.getKeyCode() == KeyEvent.VK_PLUS,
            "Add (or press +)"
    ),
    SUBTRACTION(
            "-",
            (a, b) -> a - b,
            e -> !e.isAltDown() && (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_SUBTRACT),
            "Subtract (or press -)"
    ),
    MULTIPLICATION(
            "×",
            (a, b) -> a * b,
            e -> e.getKeyCode() == KeyEvent.VK_MULTIPLY,
            "Multiply (or press *)"
    ),
    DIVISION(
            "÷",
            (a, b) -> a / b,
            e -> e.getKeyCode() == KeyEvent.VK_SLASH || e.getKeyCode() == KeyEvent.VK_DIVIDE,
            "Divide (or press /)"
    );

    private final String operator;
    private final BiFunction<Double, Double, Double> calculateFunction;

    private final Predicate<KeyEvent> keyPredicate;
    private final String tooltip;
    private final OperatorButton button;

    Operator(String operator, BiFunction<Double, Double, Double> calculateFunction, Predicate<KeyEvent> keyPredicate, String tooltip) {
        this.operator = operator;
        this.calculateFunction = calculateFunction;
        this.keyPredicate = keyPredicate;
        this.tooltip = tooltip;
        this.button = new OperatorButton(this);
    }

    @Override
    public String toString() {
        return operator;
    }

    public BiFunction<Double, Double, Double> getCalculateFunction() {
        return calculateFunction;
    }

    public Predicate<KeyEvent> getKeyPredicate() {
        return keyPredicate;
    }

    public String getToolTip() {
        return tooltip;
    }

    public OperatorButton getButton() {
        return button;
    }
}