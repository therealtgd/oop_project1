package view.admin;

import manage.users.UserDatabase;
import net.miginfocom.swing.MigLayout;
import view.utils.PlaceholderFocusListener;
import view.utils.QualificationsComboBox;
import view.utils.SpecializationsPanel;

import javax.swing.*;
import java.util.Map;

public abstract class LaborantDialog extends JDialog {

    private String title;
    protected JTextField usernameTxt;
    protected JTextField nameTxt;
    protected JTextField surnameTxt;
    protected JTextField salaryTxt;
    protected JTextField xpTxt;
    protected QualificationsComboBox quaComboBox;
    SpecializationsPanel specPanel;
    protected JButton confirmBtn;


    public LaborantDialog(String title) {
        super();
        this.title = title;
        laborantDialog();
    }

    @Override
    public String getTitle() {
        return title;
    }

    private void laborantDialog() {
        this.setTitle(title);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("", "[][]");
        setLayout(layout);

        usernameTxt = new JTextField();
        nameTxt = new JTextField();
        surnameTxt = new JTextField();
        salaryTxt = new JTextField();
        xpTxt = new JTextField();
        quaComboBox = new QualificationsComboBox();
        specPanel = new SpecializationsPanel();
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
        add(new JLabel("Kvalifikacija:"), "split 2, sg 1");
        add(quaComboBox, "pushx, growx, wrap");
        add(new JLabel("Iskustvo:"), "split 4, sg 1");
        add(xpTxt, "pushx, growx");
        add(specPanel, "wrap");
        add(confirmBtn, "split 2");
        add(cancelBtn);

        cancelBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

    }

    protected abstract void initActions();

    protected void processErrors(JTextField usernameTxt, JTextField nameTxt, JTextField surnameTxt, JTextField salaryTxt, Map<String, String> errCodes) {
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
