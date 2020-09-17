package view.admin.patient;

import net.miginfocom.swing.MigLayout;
import view.utils.GenderSelector;
import view.utils.PlaceholderFocusListener;

import javax.swing.*;
import java.util.Map;

public abstract class PatientDialog extends JDialog {

    private String title;
    protected JTextField usernameTxt;
    protected JTextField nameTxt;
    protected JTextField surnameTxt;
    protected JTextField LBOTxt;
    protected JTextField addressTxt;
    protected JTextField phoneTxt;
    GenderSelector genderSelector;
    protected JButton confirmBtn;

    public PatientDialog(String title) {
        super();
        this.title = title;
        patientDialog();
    }

    private void patientDialog() {
        this.setTitle(title);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("","[][]", "[][][][][][][][][]");
        setLayout(layout);

        usernameTxt = new JTextField();
        nameTxt = new JTextField();
        surnameTxt = new JTextField();
        LBOTxt = new JTextField();
        addressTxt = new JTextField();
        phoneTxt = new JTextField();
        genderSelector = new GenderSelector();
        confirmBtn = new JButton("Potvrdi");
        JButton cancelBtn = new JButton("Izađi");

        add(new JLabel("Korisničko ime:"), "split 2, sg 1");
        add(usernameTxt, "pushx, growx, wrap");
        add(new JLabel("Ime:"), "split 2, sg 1");
        add(nameTxt, "pushx, growx, wrap");
        add(new JLabel("Prezime:"), "split 2, sg 1");
        add(surnameTxt, "pushx, growx, wrap");
        add(new JLabel("LBO:"), "split 2, sg 1");
        add(LBOTxt, "pushx, growx, wrap");
        add(new JLabel("Adresa*:"), "split 2, sg 1");
        add(addressTxt, "pushx, growx, wrap");
        add(new JLabel("Telefon*:"), "split 2, sg 1");
        add(phoneTxt, "pushx, growx, wrap");
        add(new JLabel("Pol*:"), "split 2, sg 1");
        add(genderSelector, "wrap");
        add(confirmBtn, "split 2");
        add(cancelBtn);

        cancelBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
    }

    protected abstract void initActions();


    protected void processErrors(Map<String, String> errCodes) {
        if (errCodes.containsKey("username")) {
            usernameTxt.setText(String.valueOf(errCodes.get("username")));
            usernameTxt.addFocusListener(new PlaceholderFocusListener(usernameTxt, errCodes.get("username")));
        }
        if (errCodes.containsKey("name")) {
            nameTxt.setText(String.valueOf(errCodes.get("name")));
            nameTxt.addFocusListener(new PlaceholderFocusListener(nameTxt, errCodes.get("name")));
        }
        if (errCodes.containsKey("surname")) {
            surnameTxt.setText(String.valueOf(errCodes.get("surname")));
            surnameTxt.addFocusListener(new PlaceholderFocusListener(surnameTxt, errCodes.get("surname")));
        }
        if (errCodes.containsKey("LBO")) {
            LBOTxt.setText(String.valueOf(errCodes.get("LBO")));
            LBOTxt.addFocusListener(new PlaceholderFocusListener(LBOTxt, errCodes.get("LBO")));
        }
        if (errCodes.containsKey("address")) {
            addressTxt.setText(String.valueOf(errCodes.get("address")));
            addressTxt.addFocusListener(new PlaceholderFocusListener(addressTxt, errCodes.get("address")));
        }
        if (errCodes.containsKey("phone")) {
            phoneTxt.setText(String.valueOf(errCodes.get("phone")));
            phoneTxt.addFocusListener(new PlaceholderFocusListener(phoneTxt, errCodes.get("phone")));
        }
    }

}
