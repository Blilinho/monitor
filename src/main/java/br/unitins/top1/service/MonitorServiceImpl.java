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
    public void update(long id, Monitor monitor) {
        Monitor monitorexisting = repository.findById(id);
        if (monitorexisting == null) {
            throw new RuntimeException("Monitor not found");
        }
        monitorexisting.setName(monitor.getName());
        monitorexisting.setBrand(monitor.getBrand());
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
    public List<Monitor> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Monitor> listAll() {
        return repository.listAll();
    }
}