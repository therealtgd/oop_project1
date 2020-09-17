package view.admin;

import modules.DTO.MedicalTechnicianDTO;
import services.view.RegistrationServices;
import services.view.exceptions.RegistrationException;
import view.validators.Validator;

import javax.swing.*;
import java.util.Map;

public class MedTechRegistrationDialog extends MedicalTechnicianDialog {

    public MedTechRegistrationDialog() {
        super("Registracija med. tehničara");
        initActions();
    }

    @Override
    protected void initActions() {
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices();
            MedicalTechnicianDTO mDTO = rS.getMedicalTechnicianDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), salaryTxt.getText(), xpTxt.getText());
            Map<String, String> errCodes = Validator.validateMedicalTechnicianRegistration(mDTO);

            if (errCodes.size() == 0) {
                try {
                    String key = rS.registerMedicalTechnician(mDTO);
                    System.out.println("Registracija uspješna.");
                    System.out.println(key);
                } catch (RegistrationException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(MedTechRegistrationDialog.this, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                processErrors(usernameTxt, nameTxt, surnameTxt, salaryTxt, errCodes);
            }

        });
    }
}
