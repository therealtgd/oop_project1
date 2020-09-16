package view.admin;

import modules.DTO.LaborantDTO;
import modules.DTO.MedicalTechnicianDTO;
import net.miginfocom.swing.MigLayout;
import services.view.RegistrationServices;
import services.view.exceptions.RegistrationException;
import view.utils.PlaceholderFocusListener;
import view.utils.QualificationsComboBox;
import view.utils.SpecializationsPanel;
import view.validators.Validator;

import javax.swing.*;
import java.util.Map;

public class LaborantRegistrationDialog extends JDialog {

    public LaborantRegistrationDialog() {
        super();
        laborantDialog();
    }

    private void laborantDialog() {
        this.setTitle("Registracija laboranta");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        initGUI();
        this.pack();
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("", "[][]");
        setLayout(layout);

        JTextField usernameTxt = new JTextField();
        JTextField nameTxt = new JTextField();
        JTextField surnameTxt = new JTextField();
        JTextField salaryTxt = new JTextField();
        JTextField expText = new JTextField();
        QualificationsComboBox quaComboBox = new QualificationsComboBox();
        SpecializationsPanel specPanel = new SpecializationsPanel();
        JButton registerBtn = new JButton("Registruj se");
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
        add(expText, "pushx, growx");
        add(specPanel, "wrap");
        add(registerBtn, "split 2");
        add(cancelBtn);

        registerBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices();
            LaborantDTO lDTO = rS.getLaborantDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), salaryTxt.getText(), expText.getText(), (String)quaComboBox.getSelectedItem(), specPanel.getSelection());
            Map<String, String> errCodes = Validator.validateLaborantRegistration(lDTO);
//
            if (errCodes.size() == 0) {
                try {
                    String key = rS.registerLaborant(lDTO);
                    System.out.println("Registracija uspješna.");
                    System.out.println(key);
                } catch (RegistrationException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(LaborantRegistrationDialog.this, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                processErrors(usernameTxt, nameTxt, surnameTxt, salaryTxt, errCodes);
            }

        });

        cancelBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });


    }


    private void processErrors(JTextField usernameTxt, JTextField nameTxt, JTextField surnameTxt, JTextField salaryTxt, Map<String, String> errCodes) {
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
