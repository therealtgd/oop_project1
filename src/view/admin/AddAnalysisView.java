package view.admin;

import manage.DatabaseHandler;
import modules.DTO.AdminDTO;
import net.miginfocom.swing.MigLayout;
import services.utils.PasswordUtils;
import services.view.RegistrationServices;
import services.view.exceptions.RegistrationException;
import view.laborant.AnalysisTablePanel;
import view.validators.Validator;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class AddAnalysisView extends JDialog {

    private JTextField group;
    private JTextField type;
    private JTextField refValue;
    private JTextField unit;
    private JTextField cost;
    private JButton confirmBtn;
    private JButton cancelBtn;
    private DatabaseHandler dH;

    public AddAnalysisView(DatabaseHandler dH) {
        this.dH = dH;
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon2.png"));
        this.setTitle("Labaratoriji");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        initGUI();
        pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initGUI() {
        MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][]");
        setLayout(layout);

        group = new JTextField(20);
        type = new JTextField(20);
        refValue = new JTextField(20);
        unit = new JTextField(20);
        cost = new JTextField(20);
        confirmBtn = new JButton("Potvrdi");
        cancelBtn = new JButton("Izađi");

        add(new JLabel("Gruopa:"), "sg 1");
        add(group, "pushx, growx");
        add(new JLabel("Tip:"), "sg 1");
        add(type, "pushx, growx");
        add(new JLabel("Ref. vrednost:"), " sg 1");
        add(refValue, "pushx, growx");
        add(new JLabel("Jed. mere:"), " sg 1");
        add(unit, "pushx, growx");
        add(new JLabel("Cena:"), " sg 1");
        add(cost, "pushx, growx");
        add(confirmBtn, "split 2");
        add(cancelBtn);

         cancelBtn.addActionListener(e -> {
            setVisible(false);
            dispose();
        });
//         initActions();

    }

//    private void initActions() {
//        confirmBtn.addActionListener(e -> {
//            RegistrationServices rS = new RegistrationServices(dH);
//            AnalysisDTO aDTO = rS.getAnalysisDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText());
//            String key = PasswordUtils.generateRandomAlphanumericString(10);
//            aDTO.setPassword(key);
//            Map<String, String> errCodes = Validator.validateAdminRegistration(aDTO);
//
//            if (errCodes.size() == 0) {
//                try {
//                    rS.registerAdmin(aDTO);
//                    System.out.println("Registracija uspješna.");
//                    System.out.println(key);
//                    dispose();
//                } catch (RegistrationException ex) {
//                    System.out.println(ex.getMessage());
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
//                }
//
//            } else {
//                processErrors(errCodes);
//            }
//
//        });
//    }

}
