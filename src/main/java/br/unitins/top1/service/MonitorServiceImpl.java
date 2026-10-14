package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.HardwareSpec;
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
    public void update(Long id, Monitor monitor) {
        Monitor existing = repository.findById(id);
        if (existing == null) {
            throw new NotFoundException("Monitor not found.");
        }

        existing.setName(monitor.getName());
        existing.setPrice(monitor.getPrice());
        existing.setScreenSize(monitor.getScreenSize());
        existing.setPanelType(monitor.getPanelType());
        existing.setBrand(monitor.getBrand());

        // Update 1:1 composition (HardwareSpec)
        if (monitor.getHardwareSpec() != null) {
            if (existing.getHardwareSpec() == null) {
                existing.setHardwareSpec(monitor.getHardwareSpec());
            } else {
                existing.getHardwareSpec().setWeight(monitor.getHardwareSpec().getWeight());
                existing.getHardwareSpec().setDimensions(monitor.getHardwareSpec().getDimensions());
                existing.getHardwareSpec().setPowerConsumption(monitor.getHardwareSpec().getPowerConsumption());
            }
        } else {
            existing.setHardwareSpec(null);
        }

        // Update N:N features and accessories
        existing.setFeatures(monitor.getFeatures());
        existing.setCompatibleAccessories(monitor.getCompatibleAccessories());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Monitor not found.");
        }
    }

    @Override
    public Monitor findById(Long id) {
        Monitor monitor = repository.findById(id);
        if (monitor == null) {
            throw new NotFoundException("Monitor not found.");
        }
        return monitor;
    }

    @Override
    public List<Monitor> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Monitor> findByBrandId(Long brandId) {
        return repository.findByBrandId(brandId);
    }

    @Override
    public List<Monitor> findAll() {
        return repository.listAll();
    }
}
