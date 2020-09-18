package view;

import modules.users.User;
import net.miginfocom.swing.MigLayout;
import services.utils.PasswordUtils;
import view.validators.Validator;

import javax.swing.*;
import java.util.Map;

public class ProfileMenu extends JMenu {

    private JMenuItem passwordItem;
    private User user;

    public ProfileMenu(User user) {
        this.user = user;
        setText("Profil");
        this.passwordItem = new JMenuItem("Promeni šifru");
        add(passwordItem);


        passwordItem.addActionListener(e -> passwordDialog());
    }

    private void passwordDialog() {
        JDialog d = new JDialog();
        d.setTitle("Promena šifre");
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setResizable(false);
        initPasswordGUI(d);
        d.pack();
        d.setVisible(true);
    }

    private void initPasswordGUI(JDialog d) {
        MigLayout layout = new MigLayout("wrap 2", "[sg 1][]", "[][][][][]");
        d.setLayout(layout);

        JLabel lblCurrPass = new JLabel("Trenutna šifra:");
        JLabel lblNewPass = new JLabel("Nova šifra:");
        JLabel lblConfirm = new JLabel("Ponovo šifru:");

        JPasswordField txtCurrPass = new JPasswordField(15);
        JPasswordField txtNewPass = new JPasswordField(15);
        JPasswordField txtConfirm = new JPasswordField(15);

        JButton btnConfirm = new JButton("Potvrdi");
        JButton btnCancel = new JButton("Poništi");

        d.add(lblCurrPass);
        d.add(txtCurrPass, "pushx, growx");
        d.add(lblNewPass);
        d.add(txtNewPass, "pushx, growx");
        d.add(lblConfirm);
        d.add(txtConfirm, "pushx, growx");
        d.add(btnConfirm);
        d.add(btnCancel);

        btnConfirm.addActionListener(e -> {
            Map<String, String> errCodes = Validator.validatePasswordChange(user, new String(txtCurrPass.getPassword()), new String(txtNewPass.getPassword()), new String(txtConfirm.getPassword()));
            if (errCodes.isEmpty())
                    user.setPassword(PasswordUtils.generateRandomPass(new String(txtNewPass.getPassword())));
                else
                    processErrors(errCodes);
        });

    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }

    protected void processErrors(Map<String, String> errCodes) {
        if (errCodes.containsKey("currPass")) {
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("currPass")), "Greška - Stara šifra", JOptionPane.ERROR_MESSAGE);
        }
        if (errCodes.containsKey("newPass")) {
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("newPass")), "Greška - Nova šifra", JOptionPane.ERROR_MESSAGE);

        }
        if (errCodes.containsKey("confirmPass")) {
            JOptionPane.showMessageDialog(null, String.valueOf(errCodes.get("confirmPass")), "Greška - Ponovi šifru", JOptionPane.ERROR_MESSAGE);
        }
    }

}
