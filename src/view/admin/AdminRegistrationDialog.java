package view.admin;

import manage.DatabaseHandler;
import modules.DTO.AdminDTO;
import net.miginfocom.swing.MigLayout;
import services.utils.PasswordUtils;
import services.view.RegistrationServices;
import services.view.exceptions.RegistrationException;
import view.utils.PlaceholderFocusListener;
import view.validators.Validator;

import javax.swing.*;
import java.util.Map;

public class AdminRegistrationDialog extends JDialog {

    private JTextField usernameTxt;
    private JTextField nameTxt;
    private JTextField surnameTxt;
    private JButton confirmBtn;
    private DatabaseHandler dH;

    public AdminRegistrationDialog(DatabaseHandler dH) {
        super();
        patientDialog();
    }

    private void patientDialog() {
        this.setTitle("Registracija admina");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][]");
        setLayout(layout);

        usernameTxt = new JTextField(20);
        nameTxt = new JTextField(20);
        surnameTxt = new JTextField(20);
        confirmBtn = new JButton("Potvrdi");
        JButton cancelBtn = new JButton("Izađi");

        add(new JLabel("Korisničko ime:"), "sg 1");
        add(usernameTxt, "pushx, growx");
        add(new JLabel("Ime:"), "sg 1");
        add(nameTxt, "pushx, growx");
        add(new JLabel("Prezime:"), " sg 1");
        add(surnameTxt, "pushx, growx");
        add(confirmBtn, "split 2");
        add(cancelBtn);

        cancelBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        initActions();

    }

    private void initActions() {
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices(dH);
            AdminDTO aDTO = rS.getAdminDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText());
            String key = PasswordUtils.generateRandomAlphanumericString(10);
            aDTO.setPassword(key);
            Map<String, String> errCodes = Validator.validateAdminRegistration(aDTO);

            if (errCodes.size() == 0) {
                try {
                    rS.registerAdmin(aDTO);
                    System.out.println("Registracija uspješna.");
                    System.out.println(key);
                    dispose();
                } catch (RegistrationException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                processErrors(errCodes);
            }

        });
    }


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

    }
}
