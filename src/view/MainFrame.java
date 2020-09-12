package view;

import main.Main;
import net.miginfocom.swing.MigLayout;
import view.validators.Validator;
import view.validators.exceptions.LoginException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {

    public MainFrame() throws HeadlessException {

        loginDialog();
        mainFrame();

    }

    private void loginDialog() {
        JDialog d = new JDialog();
        d.setLocationRelativeTo(null);
        d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        d.setResizable(false);
        initLoginGUI(d);
        d.pack();
        d.setVisible(true);
    }

    private void initLoginGUI(JDialog d) {

        MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]");
        d.setLayout(layout);

        JTextField tfUsername = new JTextField(20);
        JPasswordField pfPassword = new JPasswordField(20);
        JButton btnOk = new JButton("Uloguj se");
        JButton bntCancel = new JButton("Izađi");

        d.getRootPane().setDefaultButton(btnOk);

        d.add(new JLabel("Dobrodošli. Ulogujte se."), "span 2");
        d.add(new JLabel("Korisničko ime"));
        d.add(tfUsername);
        d.add(new JLabel("Šifra"));
        d.add(pfPassword);
        d.add(btnOk, "split 2");
        d.add(bntCancel);

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = tfUsername.getText().trim();
                String password = new String(pfPassword.getPassword()).trim();
                System.out.println(username + " " + password);

                try {
                    Validator.validateLogin(username, password);

                } catch (LoginException ex) {
                    ex.printStackTrace();
                }



                d.setVisible(false);
                d.dispose();
                //Frame ulogovanog korisnika
            }
        });
    }

    private void mainFrame() {

        this.setTitle("Labaratorij ");

    }

    public static void main(String[] args) {

        MainFrame mf = new MainFrame();

    }

}


