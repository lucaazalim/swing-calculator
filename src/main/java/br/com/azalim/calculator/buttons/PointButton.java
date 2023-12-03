package br.com.azalim.calculator.buttons;

import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.Calculator;
import br.com.azalim.calculator.Constants;

import java.awt.event.KeyEvent;

import static br.com.azalim.calculator.Calculator.INSTANCE;

public class PointButton extends Button {

    public PointButton() {

        super(".", e -> e.getKeyCode() == KeyEvent.VK_PERIOD || e.getKeyCode() == KeyEvent.VK_DECIMAL);

        setBackground(Constants.GRAY);

    }

    @Override
    public void onType() {

        INSTANCE.setCurrentNumber(currentNumber -> {

            if (currentNumber.contains(".")) {
                return currentNumber;
            }

            return currentNumber.isBlank() ? "0." : currentNumber + ".";

        });

    }
}