package view.utils;

import com.toedter.calendar.JDateChooser;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ItemEvent;

public class HomeVisitPanel extends JPanel {

    private JCheckBox btn;
    private JPanel datePicker;

    public HomeVisitPanel() {
        initGUI();
    }

    public JCheckBox getBtn() {
        return btn;
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("", "[][]", "[]5[][]");
        setLayout(layout);
        setBorder(new TitledBorder(""));

        btn = new JCheckBox();
        datePicker = datePanel();

        add(new JLabel("Kućna posjeta:"), "split 2");
        add(btn, "wrap");
        add(datePicker);

        btn.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                datePicker.setVisible(true);
            } else {
                 datePicker.setVisible(false);
            }
        });
    }

    private JPanel datePanel() {
        JPanel p = new JPanel();
        MigLayout layout = new MigLayout("wrap 2", "[sg 1][]", "[][]");
        p.setLayout(layout);

        JCheckBox btnDate = new JCheckBox();
        JPanel datePanel = dateChooserPanel();

        p.add(new JLabel("Želim da navedem konkretan datum: "));
        p.add(btnDate);

        p.add(datePanel);

        btnDate.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                datePanel.setVisible(true);
            } else {
                datePanel.setVisible(false);
            }
        });

        p.setVisible(false);
        return p;
    }

    private JPanel dateChooserPanel() {
        JPanel p = new JPanel();
        MigLayout layout = new MigLayout("wrap 2", "[sg 1][]", "[][]");
        p.setLayout(layout);

        JDateChooser date = new JDateChooser();
        date.setDateFormatString("dd.MM.yyyy.");

        p.add(new JLabel("Izaberite datum:"));
        p.add(date);
        p.setVisible(false);

        return p;
    }

}
