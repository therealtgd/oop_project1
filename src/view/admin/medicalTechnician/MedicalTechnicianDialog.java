package view.admin.medicalTechnician;

import net.miginfocom.swing.MigLayout;
import view.utils.PlaceholderFocusListener;
import view.utils.QualificationsComboBox;

import javax.swing.*;
import java.util.Map;

public abstract class MedicalTechnicianDialog extends JDialog {

    private String title;
    protected JTextField usernameTxt;
    protected JTextField nameTxt;
    protected JTextField surnameTxt;
    protected JTextField salaryTxt;
    protected JTextField xpTxt;
    protected QualificationsComboBox quaComboBox;
    protected JButton confirmBtn;

    public MedicalTechnicianDialog(String title) {
        super();
        this.title = title;
        medicalTechnicianDialog();
    }

    private void medicalTechnicianDialog() {
        this.setTitle(title);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("", "[][]");
        setLayout(layout);

        usernameTxt = new JTextField(20);
        nameTxt = new JTextField(20);
        surnameTxt = new JTextField(20);
        salaryTxt = new JTextField(20);
        xpTxt = new JTextField(20);
        quaComboBox = new QualificationsComboBox();
        confirmBtn = new JButton("Potvrdi");
        JButton cancelBtn = new JButton("Izađi");

        add(new JLabel("Korisničko ime:"), "split 2, sg 1");
        add(usernameTxt, "pushx, growx, wrap");
        add(new JLabel("Ime:"), "split 2, sg 1");
        add(nameTxt, "pushx, growx, wrap");
        add(new JLabel("Prezime:"), "split 2, sg 1");
        add(surnameTxt, "pushx, growx, wrap");
        add(new JLabel("Platna osnova:"), "split 2, sg 1");
        add(salaryTxt, "pushx, growx, wrap");
//        add(new JLabel("Kvalifikacija:"), "split 2, sg 1");
//        add(quaComboBox, "pushx, growx, wrap");
        add(new JLabel("Iskustvo:"), "split 2, sg 1");
        add(xpTxt, "pushx, growx, wrap");
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
        if (errCodes.containsKey("salaryBase")) {
            salaryTxt.setText(String.valueOf(errCodes.get("salaryBase")));
            salaryTxt.addFocusListener(new PlaceholderFocusListener(salaryTxt, errCodes.get("salaryBase")));
        }
    }

}
