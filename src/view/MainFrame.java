package view;

import manage.DatabaseHandler;
import modules.DTO.PatientDTO;
import net.miginfocom.swing.MigLayout;
import services.view.LoginServices;
import services.view.RegistrationServices;
import services.view.exceptions.RegistrationException;
import view.utils.PlaceholderFocusListener;
import view.validators.Validator;
import view.validators.exceptions.LoginException;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainFrame extends JDialog {

    DatabaseHandler databaseHandler;

    public MainFrame() throws HeadlessException {
        mainFrame();
    }

    private void mainFrame() {
        this.setTitle("Labaratoriji");
        this.setSize(500, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initMainGUI();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    private void initMainGUI() {

        MigLayout layout = new MigLayout("fill", "[center]");
        this.setLayout(layout);

        JLabel lblLogo = new JLabel("Laboratorij");
        lblLogo.setFont(new Font("Sans-Serif", Font.PLAIN, 50));
        JButton btnLogin = new JButton("Prijava");
        JButton btnRegister = new JButton("Registracija");

        this.getRootPane().setDefaultButton(btnLogin);

        this.add(lblLogo, "span, sg 1, wrap");
        this.add(btnLogin, "wrap, sg 1");
        this.add(btnRegister, "sg 1");

        btnLogin.addActionListener(e -> loginDialog());

        btnRegister.addActionListener(e -> registerDialog());

    }

    private void loginDialog() {
        JDialog d = new JDialog();
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setResizable(false);
        initLoginGUI(d);
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }

    private void initLoginGUI(JDialog d) {

        MigLayout layout = new MigLayout("wrap 2", "[]", "[]20[][]20[]");
        d.setLayout(layout);

        JTextField tfUsername = new JTextField(20);
        JPasswordField pfPassword = new JPasswordField(20);
        JButton btnOk = new JButton("Uloguj se");
        JButton bntCancel = new JButton("Izađi");

        d.getRootPane().setDefaultButton(btnOk);

        d.add(new JLabel("Dobrodošli. Ulogujte se."), "span 2");
        d.add(new JLabel("Korisničko ime"), "split 2, sg 1");
        d.add(tfUsername, "pushx, growx, wrap");
        d.add(new JLabel("Šifra"), "split 2, sg 1");
        d.add(pfPassword, "pushx, growx, wrap");
        d.add(btnOk, "split 2, sg 2");
        d.add(bntCancel, "sg 2");

        btnOk.addActionListener(e -> {
            String username = tfUsername.getText().trim();
            String password = new String(pfPassword.getPassword()).trim();
            System.out.println(username + " " + password);


            LoginServices lS = new LoginServices();
            try {
                Validator.validateLogin(username, password);
                lS.login(username, password);
                System.out.println("Login uspješan.");
            } catch (LoginException ex) {
                System.out.println(ex.getMessage());
                return;
            }


            d.setVisible(false);
            d.dispose();
        });

        bntCancel.addActionListener(e -> {
            d.setVisible(false);
            d.dispose();
        });
    }

    private void registerDialog() {
        JDialog d = new JDialog();
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setResizable(false);
        initRegisterGUI(d);
        d.setSize(600, 300);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }

    private void initRegisterGUI(JDialog d) {

        MigLayout layout = new MigLayout("", "[][][]");
        d.setLayout(layout);

        JTextField usernameTxt = new JTextField();
        JPasswordField passwordTxt = new JPasswordField();
        JTextField nameTxt = new JTextField();
        JTextField surnameTxt = new JTextField();
        JTextField LBOTxt = new JTextField();
        JTextField addressTxt = new JTextField();
        JTextField phoneTxt = new JTextField();
        JRadioButton maleRBtn = new JRadioButton();
        JRadioButton femaleRBtn = new JRadioButton();
        ButtonGroup genderBtnGrp = new ButtonGroup();
        genderBtnGrp.add(maleRBtn);
        genderBtnGrp.add(femaleRBtn);
        JLabel errMessage = new JLabel("");
        JButton registerBtn = new JButton("Registruj se");
        JButton cancelBtn = new JButton("Izađi");

        d.add(new JLabel("Korisničko ime:"), "split 2, sg 1");
        d.add(usernameTxt, "pushx, growx, wrap");
        d.add(new JLabel("Šifra:"), "split 2, sg 1");
        d.add(passwordTxt, "pushx, growx, wrap");
        d.add(new JLabel("Ime:"), "split 2, sg 1");
        d.add(nameTxt, "pushx, growx, wrap");
        d.add(new JLabel("Prezime:"), "split 2, sg 1");
        d.add(surnameTxt, "pushx, growx, wrap");
        d.add(new JLabel("LBO:"), "split 2, sg 1");
        d.add(LBOTxt, "pushx, growx, wrap");
        d.add(new JLabel("Adresa*:"), "split 2, sg 1");
        d.add(addressTxt, "pushx, growx, wrap");
        d.add(new JLabel("Telefon*:"), "split 2, sg 1");
        d.add(phoneTxt, "pushx, growx, wrap");
        d.add(new JLabel("Pol*:"), "split 5, sg 1");
        d.add(new JLabel("muško"));
        d.add(maleRBtn);
        d.add(new JLabel("žensko"));
        d.add(femaleRBtn, "wrap");
        d.add(errMessage, "span, wrap");
        d.add(registerBtn, "split 2");
        d.add(cancelBtn);

        registerBtn.addActionListener(e -> {
            PatientDTO pDTO = getPatientAccountDTO(usernameTxt, passwordTxt, nameTxt, surnameTxt, LBOTxt, addressTxt, phoneTxt, maleRBtn, femaleRBtn);
            Map<String, String> errCodes = Validator.validatePatientRegistration(pDTO);

            if (errCodes.size() == 0) {
                RegistrationServices rS = new RegistrationServices();
                setOptionalParams(pDTO);
                try {
                    rS.registerPatient(pDTO);
                } catch (RegistrationException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(d, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                System.out.println("Registracija uspješna.");

            } else {
                processErrors(d, usernameTxt, nameTxt, surnameTxt, LBOTxt, addressTxt, phoneTxt, errCodes);
            }

        });

        cancelBtn.addActionListener(e -> {
            d.setVisible(false);
            d.dispose();
        });

    }

    private void setOptionalParams(PatientDTO pDTO) {
        if (pDTO.getAddress().isEmpty()) pDTO.setAddress("null");
        if (pDTO.getPhone().isEmpty()) pDTO.setPhone("null");
        if (pDTO.getGender().isEmpty()) pDTO.setGender("null");
    }

    private void processErrors(JDialog d, JTextField usernameTxt, JTextField nameTxt, JTextField surnameTxt, JTextField LBOTxt, JTextField addressTxt, JTextField phoneTxt, Map<String, String> errCodes) {
        if (errCodes.containsKey("username")) {
            usernameTxt.setText(String.valueOf(errCodes.get("username")));
            usernameTxt.addFocusListener(new PlaceholderFocusListener(usernameTxt, errCodes.get("username")));
        }
        if (errCodes.containsKey("password")) {
            JOptionPane.showMessageDialog(d, String.valueOf(errCodes.get("password")), "Šifra nije validna", JOptionPane.ERROR_MESSAGE);
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

    private PatientDTO getPatientAccountDTO(JTextField usernameTxt, JPasswordField passwordTxt, JTextField nameTxt, JTextField surnameTxt, JTextField LBOTxt, JTextField addressTxt, JTextField phoneTxt, JRadioButton maleRBtn, JRadioButton femaleRBtn) {
        PatientDTO pDTO = new PatientDTO(usernameTxt.getText(), new String(passwordTxt.getPassword()),
                nameTxt.getText(), surnameTxt.getText(), LBOTxt.getText());
        if (!addressTxt.getText().isEmpty())
            pDTO.setAddress(addressTxt.getText());
        if (!phoneTxt.getText().isEmpty())
            pDTO.setPhone(phoneTxt.getText());
        setPDTOGender(maleRBtn, femaleRBtn, pDTO);
        return pDTO;
    }

    private void setPDTOGender(JRadioButton maleRBtn, JRadioButton femaleRBtn, PatientDTO pDTO) {
        String gender = "";
        if (maleRBtn.isSelected())
            gender = "MUŠKO";
        else if (femaleRBtn.isSelected())
            gender = "ŽENSKO";
        pDTO.setGender(gender);
    }

    public static void main(String[] args) {

        MainFrame mf = new MainFrame();

    }

}


