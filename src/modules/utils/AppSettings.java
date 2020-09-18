package modules.utils;

public class AppSettings {

    private String adminFilename;
    private String laborantFilename;
    private String medicalTechnicianFilename;
    private String patientFilename;
    private String analysisFilename;
    private String analysisGroupFilename;
    private String measurementFilename;
    private String analysisRequestFilename;
    private String notificationFilename;

    public AppSettings(String adminFilename,
                       String laborantFilename,
                       String medicalTechnicianFilename,
                       String patientFilename,
                       String analysisFilename,
                       String analysisGroupFilename,
                       String measurementFilename,
                       String analysisRequestFilename,
                       String notificationFilename) {
        this.adminFilename = adminFilename;
        this.laborantFilename = laborantFilename;
        this.medicalTechnicianFilename = medicalTechnicianFilename;
        this.patientFilename = patientFilename;
        this.analysisFilename = analysisFilename;
        this.analysisGroupFilename = analysisGroupFilename;
        this.measurementFilename = measurementFilename;
        this.analysisRequestFilename = analysisRequestFilename;
        this.notificationFilename = notificationFilename;
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

    public String getAnalysisGroupFilename() { return analysisGroupFilename; }

    public String getMeasurementFilename() {
        return measurementFilename;
    }

    public String getAnalysisRequestFilename() {
        return analysisRequestFilename;
    }

    public String getNotificationFilename() {
        return notificationFilename;
    }
}
