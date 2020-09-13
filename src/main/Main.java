package main;

import modules.DTO.PatientAccountDTO;
import services.user.MedicalTechnicianServices;
import services.user.MedicalTechnicianServicesInjector;

public class Main {

    public static void main(String[] args) {
        PatientAccountDTO pDTO = new PatientAccountDTO("nekokorime", "nekoime", "nekoprezime", "nekiLBO");
        MedicalTechnicianServices services = MedicalTechnicianServicesInjector.services();
        String password = services.registerPatient(pDTO);
        System.out.println(password);
    }


}
