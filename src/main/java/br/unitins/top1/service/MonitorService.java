package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Monitor;

public interface MonitorService {
    Monitor create(Monitor monitor);
    void update(Long id, Monitor monitor);
    void delete(Long id);
    Monitor findById(Long id);
    List<Monitor> findByName(String name);
    List<Monitor> findByBrandId(Long brandId);
    List<Monitor> findAll();
}
