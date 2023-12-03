package br.com.azalim.calculator.components;

import br.com.azalim.calculator.Constants;

import javax.swing.*;
import java.awt.*;

public class Display extends JLabel {

    public Display() {

        super("0", SwingConstants.RIGHT);

        setForeground(Color.WHITE);
        setFont(new Font(null, Font.PLAIN, Constants.ORIGINAL_DISPLAY_FONT_SIZE));

    }

}