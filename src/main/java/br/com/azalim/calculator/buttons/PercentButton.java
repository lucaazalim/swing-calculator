package br.com.azalim.calculator.buttons;

import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.Calculator;
import br.com.azalim.calculator.Constants;
import static br.com.azalim.calculator.Calculator.INSTANCE;

import java.awt.event.KeyEvent;

public class PercentButton extends Button {

    public PercentButton() {

        super("%", e -> e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_5);

        setBackground(Constants.DARK_GRAY);
        setToolTipText("Percent (or press %)");

    }

    @Override
    public void onType() {

        INSTANCE.setCurrentNumber(currentNumber -> {

            if (INSTANCE.getOperator() == null) {
                return INSTANCE.formatNumber(Double.parseDouble(currentNumber) / 100);
            }

            double firstNumber = Double.parseDouble(INSTANCE.getFirstNumber());
            return INSTANCE.formatNumber(firstNumber / 100 * firstNumber);

        });

    }
}