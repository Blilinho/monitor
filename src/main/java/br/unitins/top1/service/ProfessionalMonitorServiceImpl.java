package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.ProfessionalMonitor;
import br.unitins.top1.repository.ProfessionalMonitorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ProfessionalMonitorServiceImpl implements ProfessionalMonitorService {

    @Inject
    ProfessionalMonitorRepository repository;

    @Override
    @Transactional
    public ProfessionalMonitor create(ProfessionalMonitor monitor) {
        repository.persist(monitor);
        return monitor;
    }

    @Override
    @Transactional
    public void update(Long id, ProfessionalMonitor monitor) {
        ProfessionalMonitor existing = repository.findById(id);
        if (existing == null) {
            throw new NotFoundException("Professional monitor not found.");
        }

        existing.setName(monitor.getName());
        existing.setPrice(monitor.getPrice());
        existing.setScreenSize(monitor.getScreenSize());
        existing.setPanelType(monitor.getPanelType());
        existing.setBrand(monitor.getBrand());

        existing.setColorAccuracy(monitor.getColorAccuracy());
        existing.setHeightAdjustment(monitor.getHeightAdjustment());

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
            throw new NotFoundException("Professional monitor not found.");
        }
    }

    @Override
    public ProfessionalMonitor findById(Long id) {
        ProfessionalMonitor monitor = repository.findById(id);
        if (monitor == null) {
            throw new NotFoundException("Professional monitor not found.");
        }
        return monitor;
    }

    @Override
    public List<ProfessionalMonitor> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<ProfessionalMonitor> findAll() {
        return repository.listAll();
    }
}
