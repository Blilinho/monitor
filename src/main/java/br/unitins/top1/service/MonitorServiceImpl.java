package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Monitor;
import br.unitins.top1.repository.MonitorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

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
            throw new RuntimeException("Monitor not found");
        }
        existingMonitor.setName(monitor.getName());
        existingMonitor.setBrand(monitor.getBrand());
        repository.persist(existingMonitor);
        return existingMonitor;
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Monitor findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Monitor> findByName(String nome) {
        return repository.findByName(nome);
    }

    @Override
    public List<Monitor> findByBrand(String marca) {
        return repository.findByBrand(marca);
    }

    @Override
    public List<Monitor> listAll() {
        return repository.listAll();
    }
}