package view.admin.patient;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.DTO.PatientDTO;
import modules.users.Patient;
import services.utils.Builder;
import services.view.RegistrationServices;
import view.validators.Validator;

import java.util.Map;

public class PatientEditDialog extends PatientDialog {

    private DatabaseHandler dH;
    private UserDatabase patientDatabase;
    private Patient editPatient;

    public PatientEditDialog(DatabaseHandler dH, Patient editPatient) {
        super("Uređivanje pacjenta");
        this.dH = dH;
        this.patientDatabase = patientDatabase;
        this.editPatient = editPatient;
        initActions();

    }

    @Override
    protected void initActions() {
        fill();
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices(dH);
            PatientDTO pDTO = rS.getPatientDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), LBOTxt.getText(), addressTxt.getText(), phoneTxt.getText(), genderSelector.getSelection());
            Map<String, String> errCodes = Validator.validatePatientRegistration(pDTO);

            if (errCodes.size() == 0) {
                pDTO.setPassword("temp");
                Patient p = Builder.buildPatient(pDTO);
                p.setId(editPatient.getId());
                patientDatabase.edit(p);
                dispose();
            } else {
                processErrors(errCodes);
            }

        });
    }

    private void fill() {
        usernameTxt.setText(editPatient.getUsername());
        nameTxt.setText(editPatient.getName());
        surnameTxt.setText(editPatient.getSurname());
        LBOTxt.setText(editPatient.getLBO());
        addressTxt.setText(editPatient.getAddress());
        phoneTxt.setText(editPatient.getPhone());
        genderSelector.select(String.valueOf(editPatient.getGender()));
    }
}
