package br.com.azalim.calculator.components;

import br.com.azalim.calculator.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class Button extends JButton {

    public final Predicate<KeyEvent> keyPredicate;

    private Color originalColor;

    public int gridWidth = 1;

    public Button(String name, Predicate<KeyEvent> keyPredicate) {

        super(name);

        this.keyPredicate = keyPredicate;

        setFocusable(false);
        setOpaque(true);
        setBorder(null);
        setFont(new Font(null, Font.PLAIN, 20));
        setForeground(Color.WHITE);

        addActionListener(e -> onType());

        Calculator.INSTANCE.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                if (keyPredicate.test(e)) {
                    onPressed();
                    onType();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (keyPredicate.test(e)) {
                    onReleased();
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onPressed();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                onReleased();
            }
        });

    }

    public void onPressed() {
        if (originalColor == null) {
            originalColor = getBackground();
            setBackground(getBackground().darker());
        }
    }

    public abstract void onType();

    public void onReleased() {
        setBackground(originalColor);
        originalColor = null;
    }

}