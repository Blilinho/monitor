package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.GamingMonitor;
import br.unitins.top1.repository.GamingMonitorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class GamingMonitorServiceImpl implements GamingMonitorService {

    @Inject
    GamingMonitorRepository repository;

    @Override
    @Transactional
    public GamingMonitor create(GamingMonitor monitor) {
        repository.persist(monitor);
        return monitor;
    }

    @Override
    @Transactional
    public void update(Long id, GamingMonitor monitor) {
        GamingMonitor existing = repository.findById(id);
        if (existing == null) {
            throw new NotFoundException("Gaming monitor not found.");
        }

        existing.setName(monitor.getName());
        existing.setPrice(monitor.getPrice());
        existing.setScreenSize(monitor.getScreenSize());
        existing.setPanelType(monitor.getPanelType());
        existing.setBrand(monitor.getBrand());

        existing.setRefreshRate(monitor.getRefreshRate());
        existing.setResponseTime(monitor.getResponseTime());
        existing.setGsyncSupport(monitor.getGsyncSupport());

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

        existing.setFeatures(monitor.getFeatures());
        existing.setCompatibleAccessories(monitor.getCompatibleAccessories());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Gaming monitor not found.");
        }
    }

    @Override
    public GamingMonitor findById(Long id) {
        GamingMonitor monitor = repository.findById(id);
        if (monitor == null) {
            throw new NotFoundException("Gaming monitor not found.");
        }
        return monitor;
    }

    @Override
    public List<GamingMonitor> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<GamingMonitor> findAll() {
        return repository.listAll();
    }
}
