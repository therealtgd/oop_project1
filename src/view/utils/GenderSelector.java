package view.utils;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class GenderSelector extends JPanel {

    private JRadioButton maleRBtn;
    private JRadioButton femaleRBtn;
    private ButtonGroup bG;

    public GenderSelector() {
        genderSelector();

    }

    private void genderSelector() {
        initGUI();
        setVisible(true);
    }

    private void initGUI() {

        MigLayout layout = new MigLayout("", "[sg 1][sg 1]", "[]");
        setLayout(layout);

        maleRBtn = new JRadioButton("muško");
        femaleRBtn = new JRadioButton("žensko");

        ButtonGroup bG = new ButtonGroup();
        bG.add(maleRBtn);
        bG.add(femaleRBtn);

        add(maleRBtn);
        add(femaleRBtn);

    }

    public String getSelection() {
        if (maleRBtn.isSelected())
            return maleRBtn.getText();
        if (femaleRBtn.isSelected())
            return femaleRBtn.getText();
        return null;
    }

    public void select(String gender) {
        if (maleRBtn.getText().equals(gender)) {
            maleRBtn.setSelected(true);
        } else if (femaleRBtn.getText().equals(gender)) {
            femaleRBtn.setSelected(true);
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        GenderSelector gS = new GenderSelector();
        f.add(gS, BorderLayout.CENTER);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.pack();
        f.setVisible(true);
    }
}
