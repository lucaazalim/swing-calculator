package br.com.azalim.calculator.buttons;

import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.Constants;
import static br.com.azalim.calculator.Calculator.INSTANCE;

import java.awt.event.KeyEvent;

public class EqualsButton extends Button {

    public EqualsButton() {

        super("=", e -> (!e.isShiftDown() && e.getKeyCode() == KeyEvent.VK_EQUALS) || e.getKeyCode() == KeyEvent.VK_ENTER);

        setBackground(Constants.ORANGE);
        setToolTipText("Equal (or press Return)");

    }

    @Override
    public void onType() {

        if (INSTANCE.getSecondNumber().isBlank()) {
            INSTANCE.setSecondNumber(Double.parseDouble(INSTANCE.getFirstNumber()));
        }

        INSTANCE.calculate();
        INSTANCE.setOperatorButton(null);

    }

}
