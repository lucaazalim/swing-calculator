package br.com.azalim.calculator.buttons;

import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.Calculator;
import br.com.azalim.calculator.Constants;

import java.awt.event.KeyEvent;

import static br.com.azalim.calculator.Calculator.INSTANCE;

public class SignButton extends Button {

    public SignButton() {

        super("±", e -> e.isAltDown() && e.getKeyCode() == KeyEvent.VK_MINUS);

        setBackground(Constants.DARK_GRAY);
        setToolTipText("Negate the displayed value (or press Option-Minus [-])");


    }

    @Override
    public void onType() {

        if (INSTANCE.getSecondNumber().isBlank()) {
            if (!INSTANCE.getFirstNumber().isBlank()) {
                INSTANCE.setFirstNumber(Double.parseDouble(INSTANCE.getFirstNumber()) * -1);
            }
        } else {
            INSTANCE.setSecondNumber(Double.parseDouble(INSTANCE.getSecondNumber()) * -1);
        }

        INSTANCE.updateDisplay();

    }
}