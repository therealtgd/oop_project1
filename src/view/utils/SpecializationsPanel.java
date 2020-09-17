package view.utils;

import com.sun.glass.events.KeyEvent;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpecializationsPanel extends JPanel {

    List<String> specs = Arrays.asList(
            "Alergologija",
            "Imunohemija",
            "Genetika",
            "Biohemija",
            "Imunologija",
            "Serologija");
    List<JCheckBox> checkBoxes;

    public SpecializationsPanel() {
        this.checkBoxes = new ArrayList<>();
        initGUI();
        setVisible(true);

    }

    public List<JCheckBox> getCheckBoxes() {
        return checkBoxes;
    }

    public void addCheckBox(JCheckBox checkBox) {
        checkBoxes.add(checkBox);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][]");
        setLayout(layout);

        Border border = new TitledBorder("Specijalizacije");
        setBorder(border);

        JCheckBox cbAlergo = new JCheckBox(specs.get(0));
        cbAlergo.setMnemonic(KeyEvent.VK_1);
        checkBoxes.add(cbAlergo);

        JCheckBox cbImunoHem = new JCheckBox(specs.get(1));
        cbImunoHem.setMnemonic(KeyEvent.VK_2);
        checkBoxes.add(cbImunoHem);

        JCheckBox cbGene = new JCheckBox(specs.get(2));
        cbGene.setMnemonic(KeyEvent.VK_3);
        checkBoxes.add(cbGene);

        JCheckBox cbBio = new JCheckBox(specs.get(3));
        cbBio.setMnemonic(KeyEvent.VK_4);
        checkBoxes.add(cbBio);

        JCheckBox cbImunoLog = new JCheckBox(specs.get(4));
        cbImunoLog.setMnemonic(KeyEvent.VK_5);
        checkBoxes.add(cbImunoLog);

        JCheckBox cbSero = new JCheckBox(specs.get(5));
        cbSero.setMnemonic(KeyEvent.VK_6);
        checkBoxes.add(cbSero);

        add(cbAlergo, "sg 1");
        add(cbImunoHem, "sg 1");
        add(cbGene, "sg 1");
        add(cbBio, "sg 1");
        add(cbImunoLog, "sg 1");
        add(cbSero, "sg 1");
    }

    public ArrayList<String> getSelection() {
        ArrayList<String> retVal = new ArrayList<>();
        for (JCheckBox cb : checkBoxes) {
            if (cb.isSelected())
                retVal.add(cb.getText());
        }
        return retVal;
    }

    public void setSelection(List<String> selections) {
        for (String s : selections) {
            for (JCheckBox cb : checkBoxes) {
                if (cb.getText().equals(s))
                    cb.setSelected(true);
            }
        }
    }

}
