package view.admin;

import net.miginfocom.swing.MigLayout;
import view.utils.QualificationsComboBox;
import view.utils.SpecializationsPanel;

import javax.swing.*;

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
//        add(new JLabel("Specijalizacije:"), "sg 1");
        add(new SpecializationsPanel(), "wrap");
        add(registerBtn, "split 2");
        add(cancelBtn);

        registerBtn.addActionListener(e -> {
//            PatientAccountDTO pDTO = getPatientAccountDTO(usernameTxt, passwordTxt, nameTxt, surnameTxt, salaryTxt, expText, phoneTxt, maleRBtn, femaleRBtn);
//            Map<String, String> errCodes = Validator.validateRegistration(pDTO);
//
//            if (errCodes.size() == 0) {
//                RegistrationServices rS = new RegistrationServices();
//                setOptionalParams(pDTO);
//                try {
//                    rS.register(pDTO);
//                } catch (RegistrationException ex) {
//                    System.out.println(ex.getMessage());
//                    JOptionPane.showMessageDialog(LaborantRegistrationDialog.this, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                System.out.println("Registracija uspješna.");
//
//            } else {
//                processErrors(LaborantRegistrationDialog.this, usernameTxt, nameTxt, surnameTxt, salaryTxt, expText, phoneTxt, errCodes);
//            }

        });

        cancelBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });


    }


}
