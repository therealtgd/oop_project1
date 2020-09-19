package main;

import modules.entities.Analysis;
import modules.entities.AnalysisGroup;
import modules.users.Patient;
import modules.utils.MyPassword;
import modules.utils.Range;

public class Main {

    public static void main(String[] args) {
//        PatientAccountDTO pDTO = new PatientAccountDTO("nekokorime", "nekoime", "nekoprezime", "nekiLBO");
//        MedicalTechnicianServices services = MedicalTechnicianServicesInjector.services();
//        String password = services.registerPatient(pDTO);
//        System.out.println(password);
//        MyPassword mP = PasswordUtils.generateRandomPass("Password");
//        Laborant l = new Laborant(0, "laborant", "labo", "labaratovic", mP, 100.10, 12, "SSS");
//        System.out.println(l.toFileString());
//
//        MyPassword mP2 = PasswordUtils.generateRandomPass("pass");
//
//        Admin a = new Admin(0, "admin", "admi", "adminic", mP2);
//        System.out.println(a.toFileString());
//
//        MyPassword mP3 = PasswordUtils.generateRandomPass("pass");
//        MedicalTechnician mT = new MedicalTechnician(0, "tehnicar", "tehno", "tehnicarevic", mP3, 50.1, 2);
//        System.out.println(mT.toFileString());

        Analysis analysis = new Analysis(0, "Alergologija", "PHADIATOP INFANT - ALERGIJA", new Range(10, 20), "mol/l", 2520);
        Analysis analysis2 = new Analysis(1, "Alergologija", "PHADIATOP (ALATOP) - ALERGENI", new Range(10, 20), "mol/l", 2020);
        Analysis analysis3 = new Analysis(2, "Alergologija", "MIX GRINJE,PRAŠINA HX2", new Range(10, 20), "mol/l", 2420);
        AnalysisGroup agAlergo = new AnalysisGroup(0, "Alergologija");
        agAlergo.addAnalyses(analysis);
        agAlergo.addAnalyses(analysis2);
        agAlergo.addAnalyses(analysis3);

        Analysis analysis4 = new Analysis(3, "Imunohemija", "LEPTIN", new Range(10, 20), "mol/l", 1210);
        Analysis analysis5 = new Analysis(4, "Imunohemija", "ADIPONEKTIN", new Range(10, 20), "mol/l", 3820);
        Analysis analysis6 = new Analysis(5, "Imunohemija", "VITAMIN B2", new Range(10, 20), "mol/l", 1810);
        AnalysisGroup agImnuoHem = new AnalysisGroup(1, "Imunohemija");
        agImnuoHem.addAnalyses(analysis4);
        agImnuoHem.addAnalyses(analysis5);
        agImnuoHem.addAnalyses(analysis6);

        Analysis analysis7 = new Analysis(6, "Genetika", "OČINSTVO- OTAC+DETE", new Range(10, 20), "mol/l", 25000);
        Analysis analysis8 = new Analysis(7, "Genetika", "LAKTOZNA INTOLERANCIJA / PCR", new Range(10, 20), "mol/l", 5100);
        Analysis analysis9 = new Analysis(8, "Genetika", "NUTRIGENETIČKE KONSULTACIJE", new Range(10, 20), "mol/l", 5000);
        AnalysisGroup agGene = new AnalysisGroup(2, "Genetika");
        agGene.addAnalyses(analysis7);
        agGene.addAnalyses(analysis8);
        agGene.addAnalyses(analysis9);


        Analysis analysis10 = new Analysis(9, "Biohemija", "KREATININ", new Range(10, 20), "mol/l", 140);
        Analysis analysis11 = new Analysis(10, "Biohemija", "HOLESTEROL", new Range(10, 20), "mol/l", 140);
        Analysis analysis12 = new Analysis(11, "Biohemija", "HDL", new Range(10, 20), "mol/l", 150);
        AnalysisGroup agBio = new AnalysisGroup(3, "Biohemija");
        agBio.addAnalyses(analysis10);
        agBio.addAnalyses(analysis11);
        agBio.addAnalyses(analysis12);

        Analysis analysis13 = new Analysis(12, "Imunologija", "IMUNOELEKTROFOREZA U URIN", new Range(10, 20), "mol/l", 3020);
        Analysis analysis14 = new Analysis(13, "Imunologija", "IMUNOELEKTROFOREZA U SERUMU", new Range(10, 20), "mol/l", 3020);
        Analysis analysis15 = new Analysis(14, "Imunologija", "C1Q KOMPLEMENT", new Range(10, 20), "mol/l", 1100);
        AnalysisGroup agImunoLog = new AnalysisGroup(4, "Imunologija");
        agImunoLog.addAnalyses(analysis13);
        agImunoLog.addAnalyses(analysis14);
        agImunoLog.addAnalyses(analysis15);

        Analysis analysis16 = new Analysis(15, "Serologija", "HAV - IGG AT", new Range(10, 20), "mol/l", 1610);
        Analysis analysis17 = new Analysis(16, "Serologija", "INFLUENZA A - IGM AT", new Range(10, 20), "mol/l", 1310);
        Analysis analysis18 = new Analysis(17, "Serologija", "WEST NILE VIRUS IGM", new Range(10, 20), "mol/l", 1510);
        AnalysisGroup agSero = new AnalysisGroup(5, "Serologija");
        agSero.addAnalyses(analysis16);
        agSero.addAnalyses(analysis17);
        agSero.addAnalyses(analysis18);

        System.out.println(analysis.toFileString());
        System.out.println(analysis2.toFileString());
        System.out.println(analysis3.toFileString());
        System.out.println(analysis4.toFileString());
        System.out.println(analysis5.toFileString());
        System.out.println(analysis6.toFileString());
        System.out.println(analysis7.toFileString());
        System.out.println(analysis8.toFileString());
        System.out.println(analysis9.toFileString());
        System.out.println(analysis10.toFileString());
        System.out.println(analysis11.toFileString());
        System.out.println(analysis12.toFileString());
        System.out.println(analysis13.toFileString());
        System.out.println(analysis14.toFileString());
        System.out.println(analysis15.toFileString());
        System.out.println(analysis16.toFileString());
        System.out.println(analysis17.toFileString());
        System.out.println(analysis18.toFileString());

        System.out.println(agAlergo.toFileString());
        System.out.println(agImnuoHem.toFileString());
        System.out.println(agGene.toFileString());
        System.out.println(agBio.toFileString());
        System.out.println(agImunoLog.toFileString());
        System.out.println(agSero.toFileString());

//        Patient p = new Patient(7,"sadasda","asdasd","asdasd", mP,"56523562","MUŠKO",null,null);
    }
}
