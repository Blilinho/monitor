package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.GamingMonitor;

public interface GamingMonitorService {
    GamingMonitor create(GamingMonitor monitor);
    void update(Long id, GamingMonitor monitor);
    void delete(Long id);
    GamingMonitor findById(Long id);
    List<GamingMonitor> findByName(String name);
    List<GamingMonitor> findAll();
}