package br.com.azalim.calculator.buttons;

import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.Constants;
import static br.com.azalim.calculator.Calculator.INSTANCE;

import java.awt.event.KeyEvent;

public class ClearButton extends Button {

    public ClearButton() {

        super("C", e -> e.getKeyCode() == KeyEvent.VK_ESCAPE || e.getKeyCode() == KeyEvent.VK_C);

        setBackground(Constants.DARK_GRAY);
        setToolTipText("Clear (Esc)");

    }

    @Override
    public void onType() {

        if (INSTANCE.getOperator() == null) {
            INSTANCE.setFirstNumber(null);
            INSTANCE.setSecondNumber(null);
        }

        INSTANCE.setOperatorButton(null);
        INSTANCE.updateDisplay();

    }

}