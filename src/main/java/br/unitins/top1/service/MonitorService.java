package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Monitor;

public interface MonitorService {
    Monitor create(Monitor monitor);
    Monitor update(long id, Monitor monitor);
    void delete(long id);
    Monitor findById(long id);
    List<Monitor> findByName(String name);
    List<Monitor> findByBrand(String brand);
    List<Monitor> listAll();
}