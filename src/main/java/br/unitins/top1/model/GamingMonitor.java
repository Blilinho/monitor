package br.unitins.top1.model;

import jakarta.persistence.Entity;

@Entity
public class GamingMonitor extends Monitor {
    
    private Integer refreshRate; 
    private Double responseTime; 
    private Boolean gsyncSupport;
    
    public Integer getRefreshRate() {
        return refreshRate;
    }
    public void setRefreshRate(Integer refreshRate) {
        this.refreshRate = refreshRate;
    }
    public Double getResponseTime() {
        return responseTime;
    }
    public void setResponseTime(Double responseTime) {
        this.responseTime = responseTime;
    }
    public Boolean getGsyncSupport() {
        return gsyncSupport;
    }
    public void setGsyncSupport(Boolean gsyncSupport) {
        this.gsyncSupport = gsyncSupport;
    } 

    
}