package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.ProfessionalMonitor;

public interface ProfessionalMonitorService {
    ProfessionalMonitor create(ProfessionalMonitor monitor);
    void update(Long id, ProfessionalMonitor monitor);
    void delete(Long id);
    ProfessionalMonitor findById(Long id);
    List<ProfessionalMonitor> findByName(String name);
    List<ProfessionalMonitor> findAll();
}
