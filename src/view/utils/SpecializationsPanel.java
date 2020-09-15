package view.utils;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
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

    public SpecializationsPanel() {
        initGUI();
        setVisible(true);

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
        JCheckBox cbImunoHem = new JCheckBox();
        JCheckBox cbGene = new JCheckBox();
        JCheckBox cbBio = new JCheckBox();
        JCheckBox cbImunoLog = new JCheckBox();
        JCheckBox cbSero = new JCheckBox();

//        ButtonGroup bg = new ButtonGroup();
//        bg.add(cbAlergo);
//        bg.add(cbImunoHem);
//        bg.add(cbGene);
//        bg.add(cbBio);
//        bg.add(cbImunoLog);
//        bg.add(cbSero);

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
}
