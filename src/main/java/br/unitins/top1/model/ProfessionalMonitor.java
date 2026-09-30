package br.unitins.top1.model;

import jakarta.persistence.Entity;

@Entity
public class ProfessionalMonitor extends Monitor {
    
    private String colorAccuracy; 
    private Boolean heightAdjustment; 
    
    public String getColorAccuracy() {
        return colorAccuracy;
    }
    public void setColorAccuracy(String colorAccuracy) {
        this.colorAccuracy = colorAccuracy;
    }
    public Boolean getHeightAdjustment() {
        return heightAdjustment;
    }
    public void setHeightAdjustment(Boolean heightAdjustment) {
        this.heightAdjustment = heightAdjustment;
    }
}
