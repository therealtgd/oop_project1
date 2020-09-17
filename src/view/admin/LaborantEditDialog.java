package view.admin;

import manage.users.UserDatabase;
import modules.DTO.LaborantDTO;
import modules.users.Laborant;
import services.utils.Builder;
import services.view.RegistrationServices;
import view.validators.Validator;

import java.util.Map;

public class LaborantEditDialog extends LaborantDialog {

    private UserDatabase laborantDatabase;
    private Laborant editLaborant;

    public LaborantEditDialog(UserDatabase laborantDatabase, Laborant editLaborant) {
        super("Izmena laboranta");
        this.laborantDatabase = laborantDatabase;
        this.editLaborant = editLaborant;
        initActions();

    }

    public Laborant getEditLaborant() {
        return editLaborant;
    }

    @Override
    protected void initActions() {
        fill();
        confirmBtn.addActionListener(e -> {
            RegistrationServices rS = new RegistrationServices();
            LaborantDTO lDTO = rS.getLaborantDTO(usernameTxt.getText(), nameTxt.getText(), surnameTxt.getText(), salaryTxt.getText(), xpTxt.getText(), (String) quaComboBox.getSelectedItem(), specPanel.getSelection());
            Map<String, String> errCodes = Validator.validateLaborantRegistration(lDTO);

            if (errCodes.size() == 0) {
                lDTO.setPassword("temp");
                Laborant laborant = Builder.buildLaborant(lDTO);
                laborant.setId(editLaborant.getId());
                laborantDatabase.edit(laborant);
                dispose();
            } else {
                processErrors(usernameTxt, nameTxt, surnameTxt, salaryTxt, errCodes);
            }

        });
    }

    private void fill() {
        usernameTxt.setText(editLaborant.getUsername());
        nameTxt.setText(editLaborant.getName());
        surnameTxt.setText(editLaborant.getSurname());
        salaryTxt.setText(String.valueOf(editLaborant.getSalaryBase()));
        xpTxt.setText(String.valueOf(editLaborant.getExperience()));
        quaComboBox.setSelectedItem(editLaborant.getQualification());
        specPanel.setSelection(editLaborant.getSpecializations());
    }
}
