package modules.DTO;

import modules.entities.Analysis;
import modules.users.Patient;

import java.util.List;

public class AnalysisRequestDTO {

    private Patient patient;
    private List<Analysis> analyses;
    private Boolean homeVisit;
    private Double price;

    public AnalysisRequestDTO(Patient patient, List<Analysis> analyses, Boolean homeVisit, Double price) {
        this.patient = patient;
        this.analyses = analyses;
        this.homeVisit = homeVisit;
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public List<Analysis> getAnalyses() {
        return analyses;
    }

    public Boolean getHomeVisit() {
        return homeVisit;
    }

    public Double getPrice() {
        return price;
    }
}
