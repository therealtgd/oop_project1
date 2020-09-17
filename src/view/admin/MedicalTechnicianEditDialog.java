package view.admin;

import manage.users.UserDatabase;
import modules.DTO.MedicalTechnicianDTO;
import modules.users.MedicalTechnician;
import services.utils.Builder;
import services.view.RegistrationServices;
import view.validators.Validator;

import java.util.Map;

public class MedicalTechnicianEditDialog extends  MedicalTechnicianDialog{
    private UserDatabase medTechnicianDatabase;
    private MedicalTechnician editMedTechnician;

    public MedicalTechnicianEditDialog(UserDatabase medTechnicianDatabase, MedicalTechnician editMedTechnician) {
        super("Izmena med. tehničara");
        this.medTechnicianDatabase = medTechnicianDatabase;
        this.editMedTechnician = editMedTechnician;
        initActions();

    }

    @Override
    protected void initActions() {
        fill();
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices();
            MedicalTechnicianDTO mTDTO = rS.getMedicalTechnicianDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), salaryTxt.getText(), xpTxt.getText());
            Map<String, String> errCodes = Validator.validateMedicalTechnicianRegistration(mTDTO);

            if (errCodes.size() == 0) {
                mTDTO.setPassword("temp");
                MedicalTechnician mT = Builder.buildMedicalTechnician(mTDTO);
                mT.setId(editMedTechnician.getId());
                medTechnicianDatabase.edit(mT);
                dispose();
            } else {
                processErrors(usernameTxt, nameTxt, surnameTxt, salaryTxt, errCodes);
            }

        });
    }

    private void fill() {
        usernameTxt.setText(editMedTechnician.getUsername());
        nameTxt.setText(editMedTechnician.getName());
        surnameTxt.setText(editMedTechnician.getSurname());
        salaryTxt.setText(String.valueOf(editMedTechnician.getSalaryBase()));
        xpTxt.setText(String.valueOf(editMedTechnician.getExperience()));
    }
}
