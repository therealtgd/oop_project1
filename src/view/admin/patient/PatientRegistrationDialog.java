package view.admin.patient;

import manage.DatabaseHandler;
import modules.DTO.PatientDTO;
import services.utils.PasswordUtils;
import services.view.RegistrationServices;
import services.view.exceptions.RegistrationException;
import view.validators.Validator;

import javax.swing.*;
import java.util.Map;

public class PatientRegistrationDialog extends PatientDialog{

    private DatabaseHandler dH;

    public PatientRegistrationDialog(DatabaseHandler dH) {
        super("Registracija pacjenta");
        this.dH = dH;
        initActions();
    }

    @Override
    protected void initActions() {
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices(dH);
            PatientDTO pDTO = rS.getPatientDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), LBOTxt.getText(), addressTxt.getText(), phoneTxt.getText(), genderSelector.getSelection());
            String key = PasswordUtils.generateRandomAlphanumericString(10);
            pDTO.setPassword(key);
            Map<String, String> errCodes = Validator.validatePatientRegistration(pDTO);

            if (errCodes.size() == 0) {
                try {
                    rS.registerPatient(pDTO);
                    System.out.println("Registracija uspješna.");
                    System.out.println(key);
                } catch (RegistrationException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(PatientRegistrationDialog.this, ex.getMessage(), "Greška prilikom registracije", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                processErrors(errCodes);
            }

        });
    }
}
