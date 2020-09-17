package view.admin;

import modules.DTO.LaborantDTO;
import services.view.RegistrationServices;
import services.view.exceptions.RegistrationException;
import view.validators.Validator;

import javax.swing.*;
import java.util.Map;

public class LaborantRegistrationDialog extends LaborantDialog {

    public LaborantRegistrationDialog() {
        super("Registracija laboranta");
        initActions();

    }

    @Override
    protected void initActions() {
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices();
            LaborantDTO lDTO = rS.getLaborantDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), salaryTxt.getText(), xpTxt.getText(), (String) quaComboBox.getSelectedItem(), specPanel.getSelection());
            Map<String, String> errCodes = Validator.validateLaborantRegistration(lDTO);

            if (errCodes.size() == 0) {
                try {
                    String key = rS.registerLaborant(lDTO);
                    System.out.println("Registracija uspješna.");
                    System.out.println(key);
                    dispose();
                } catch (RegistrationException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(LaborantRegistrationDialog.this, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                processErrors(usernameTxt, nameTxt, surnameTxt, salaryTxt, errCodes);
            }

        });
    }
}
