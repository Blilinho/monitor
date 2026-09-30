package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Monitor;
import br.unitins.top1.repository.MonitorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class MonitorServiceImpl implements MonitorService {

    @Inject
    MonitorRepository repository;

    @Override
    @Transactional
    public Monitor create(Monitor monitor) {
        repository.persist(monitor);
        return monitor;
    }

    @Override
    @Transactional
    public Monitor update(long id, Monitor monitor) {
        Monitor existingMonitor = repository.findById(id);
        if (existingMonitor == null) {
            throw new NotFoundException("Monitor not found.");
        }
        existingMonitor.setName(monitor.getName());
        existingMonitor.setBrand(monitor.getBrand());
        existingMonitor.setPanelType(monitor.getPanelType());
        existingMonitor.setPrice(monitor.getPrice());
        existingMonitor.setScreenSize(monitor.getScreenSize());
        return existingMonitor;
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Monitor not found.");
        }
    }

    @Override
    public Monitor findById(long id) {
        Monitor monitor = repository.findById(id);
        if (monitor == null) throw new NotFoundException("Monitor not found.");
        return monitor;
    }

    @Override
    public List<Monitor> findByName(String name) { return repository.findByName(name); }

    @Override
    public List<Monitor> findByBrand(String brand) { return repository.findByBrand(brand); }

    @Override
    public List<Monitor> listAll() { return repository.listAll(); }
}
