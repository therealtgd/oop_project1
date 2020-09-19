package view.admin.medicalTechnician;

import manage.DatabaseHandler;
import manage.users.UserDatabase;
import modules.DTO.MedicalTechnicianDTO;
import modules.users.MedicalTechnician;
import services.utils.Builder;
import services.view.RegistrationServices;
import view.validators.Validator;

import java.util.Map;

public class MedicalTechnicianEditDialog extends MedicalTechnicianDialog {

    private DatabaseHandler dH;
    private UserDatabase medTechnicianDatabase;
    private MedicalTechnician editMedTechnician;

    public MedicalTechnicianEditDialog(DatabaseHandler dH, MedicalTechnician editMedTechnician) {
        super("Izmena med. tehničara");
        this.dH = dH;
        this.medTechnicianDatabase = dH.getUserDatabase().getMedTechnicianDatabase();
        this.editMedTechnician = editMedTechnician;
        initActions();

    }

    @Override
    protected void initActions() {
        fill();
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices(dH);
            MedicalTechnicianDTO mTDTO = rS.getMedicalTechnicianDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), salaryTxt.getText(), xpTxt.getText());
            Map<String, String> errCodes = Validator.validateMedicalTechnicianRegistration(mTDTO);

            if (errCodes.size() == 0) {
                mTDTO.setPassword("temp");
                MedicalTechnician mT = Builder.buildMedicalTechnician(mTDTO);
                mT.setId(editMedTechnician.getId());
                medTechnicianDatabase.edit(mT);
                dispose();
            } else {
                processErrors(errCodes);
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
