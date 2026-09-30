package br.unitins.top1.model;

import jakarta.persistence.Entity;

@Entity
public class ProfessionalMonitor extends Monitor {
    
    private String colorAccuracy; 
    private Boolean heightAdjustment; 
    private PanelType panelType;
    
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
    public PanelType getPanelType() {
        return panelType;
    }
    public void setPanelType(PanelType panelType) {
        this.panelType = panelType;
    } 

    
}