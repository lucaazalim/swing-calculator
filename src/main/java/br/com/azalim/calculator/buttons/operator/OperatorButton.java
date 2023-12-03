package br.com.azalim.calculator.buttons.operator;

import br.com.azalim.calculator.components.Button;
import br.com.azalim.calculator.Calculator;
import br.com.azalim.calculator.Constants;

public class OperatorButton extends Button {

    private final Operator operator;

    public OperatorButton(Operator operator) {
        super(operator.toString(), operator.getKeyPredicate());
        this.operator = operator;

        setBackground(Constants.ORANGE);
        setToolTipText(operator.getToolTip());
    }

    public Operator getOperator() {
        return operator;
    }

    @Override
    public void onType() {

        Calculator.INSTANCE.calculate();
        Calculator.INSTANCE.setOperatorButton(this);

    }

}