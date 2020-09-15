package view.utils;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PlaceholderFocusListener implements FocusListener {

    JTextField textField;
    String errMessage;

    public PlaceholderFocusListener(JTextField textField, String errMessage) {
        this.textField = textField;
        this.errMessage = errMessage;
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (textField.getText().equals(errMessage))
            textField.setText("");
    }

    @Override
    public void focusLost(FocusEvent e) {

    }
}
