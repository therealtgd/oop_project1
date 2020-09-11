package modules.utils;

public class AppSettings {

    private String adminFilename;
    private String laborantFilename;
    private String medicalTechnicianFilename;
    private String patientFilename;
    private String analysisFilename;
    private String analysisRequestFilename;
    private String measurementFilename;

    public AppSettings(String adminFilename,
                       String laborantFilename,
                       String medicalTechnicianFilename,
                       String patientFilename,
                       String analysisFilename,
                       String analysisRequestFilename,
                       String measurementFilename) {
        this.adminFilename = adminFilename;
        this.laborantFilename = laborantFilename;
        this.medicalTechnicianFilename = medicalTechnicianFilename;
        this.patientFilename = patientFilename;
        this.analysisFilename = analysisFilename;
        this.analysisRequestFilename = analysisRequestFilename;
        this.measurementFilename = measurementFilename;
    }

    public String getAdminFilename() {
        return adminFilename;
    }

    public String getLaborantFilename() {
        return laborantFilename;
    }

    public String getMedicalTechnicianFilename() {
        return medicalTechnicianFilename;
    }

    public String getPatientFilename() {
        return patientFilename;
    }

    public String getAnalysisFilename() {
        return analysisFilename;
    }

    public String getAnalysisRequestFilename() {
        return analysisRequestFilename;
    }

    public String getMeasurementFilename() {
        return measurementFilename;
    }
}
