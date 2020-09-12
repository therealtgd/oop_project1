package main;

import modules.DTO.PatientAccountDTO;
import modules.users.Admin;
import modules.users.Laborant;
import modules.users.MedicalTechnician;
import services.MedicalTechnicianServices;
import services.MedicalTechnicianServicesInjector;

public class Main {

    public static void main(String[] args) {
        PatientAccountDTO pDTO = new PatientAccountDTO("nekokorime", "nekoime", "nekoprezime", "nekiLBO");
        MedicalTechnicianServices services = MedicalTechnicianServicesInjector.services();
        services.registerPatient(pDTO);

    }


}
