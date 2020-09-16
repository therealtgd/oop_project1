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
        MigLayout layout = new MigLayout("wrap 4", "[][][][]", "[][][]");
        setLayout(layout);

        Border border = new TitledBorder("Specijalizacije");
        setBorder(border);

        JLabel lblAlergo = new JLabel("Alergorogija");
        JLabel lblImunoHem = new JLabel("Imunohemija");
        JLabel lblGene = new JLabel("Genetika");
        JLabel lblBio = new JLabel("Biohemija");
        JLabel lblImunoLog = new JLabel("Imunologija");
        JLabel lblSero = new JLabel("Serologija");

        JCheckBox cbAlergo = new JCheckBox();
        cbAlergo.setMnemonic(KeyEvent.VK_1);
        cbAlergo.setText(specs.get(0));
        checkBoxes.add(cbAlergo);

        JCheckBox cbImunoHem = new JCheckBox();
        cbImunoHem.setMnemonic(KeyEvent.VK_2);
        cbAlergo.setText(specs.get(1));
        checkBoxes.add(cbImunoHem);

        JCheckBox cbGene = new JCheckBox();
        cbGene.setMnemonic(KeyEvent.VK_3);
        cbAlergo.setText(specs.get(2));
        checkBoxes.add(cbGene);

        JCheckBox cbBio = new JCheckBox();
        cbBio.setMnemonic(KeyEvent.VK_4);
        cbAlergo.setText(specs.get(3));
        checkBoxes.add(cbBio);

        JCheckBox cbImunoLog = new JCheckBox();
        cbImunoLog.setMnemonic(KeyEvent.VK_5);
        cbAlergo.setText(specs.get(4));
        checkBoxes.add(cbImunoLog);

        JCheckBox cbSero = new JCheckBox();
        cbSero.setMnemonic(KeyEvent.VK_6);
        cbAlergo.setText(specs.get(5));
        checkBoxes.add(cbSero);

        add(lblAlergo, "sg 1");
        add(cbAlergo);
        add(lblImunoHem, "sg 1");
        add(cbImunoHem);
        add(lblGene, "sg 1");
        add(cbGene);
        add(lblBio, "sg 1");
        add(cbBio);
        add(lblImunoLog, "sg 1");
        add(cbImunoLog);
        add(lblSero, "sg 1");
        add(cbSero);
    }

    public List<String> getSelection() {
        ArrayList<String> retVal = new ArrayList<>();
        for (JCheckBox cb: checkBoxes) {
            if (cb.isSelected())
                retVal.add(cb.getText());
        } return retVal;
    }

}
