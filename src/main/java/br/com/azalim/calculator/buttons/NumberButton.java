package br.com.azalim.calculator.buttons;

import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.Calculator;
import br.com.azalim.calculator.Constants;
import static br.com.azalim.calculator.Calculator.INSTANCE;

import java.util.List;

public class NumberButton extends Button {

    private final int number;

    public NumberButton(int number, List<Integer> keyCodes) {

        super(String.valueOf(number), e -> keyCodes.contains(e.getKeyCode()));
        this.number = number;

        setBackground(Constants.GRAY);

        if (number == 0) {
            gridWidth = 2;
        }

    }

    @Override
    public void onType() {

        INSTANCE.setCurrentNumber(currentNumber -> {

            if (number == 0 && currentNumber.equals("0")) {
                return currentNumber;
            }

            return currentNumber + number;

        });

    }

}