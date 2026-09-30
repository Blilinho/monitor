package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.dto.ProfessionalMonitorDTO;
import br.unitins.top1.dto.ProfessionalMonitorResponseDTO;
import br.unitins.top1.model.PanelType;
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
    public ProfessionalMonitorResponseDTO create(ProfessionalMonitorDTO dto) {
        ProfessionalMonitor monitor = new ProfessionalMonitor();
        apply(dto, monitor);
        repository.persist(monitor);
        return new ProfessionalMonitorResponseDTO(monitor);
    }

    @Override
    @Transactional
    public ProfessionalMonitorResponseDTO update(Long id, ProfessionalMonitorDTO dto) {
        ProfessionalMonitor monitor = findEntity(id);
        apply(dto, monitor);
        return new ProfessionalMonitorResponseDTO(monitor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) throw new NotFoundException("Professional monitor not found.");
    }

    @Override
    public ProfessionalMonitorResponseDTO findById(Long id) {
        return new ProfessionalMonitorResponseDTO(findEntity(id));
    }

    @Override
    public List<ProfessionalMonitorResponseDTO> findAll() {
        return repository.listAll().stream().map(ProfessionalMonitorResponseDTO::new).toList();
    }

    private ProfessionalMonitor findEntity(Long id) {
        ProfessionalMonitor monitor = repository.findById(id);
        if (monitor == null) throw new NotFoundException("Professional monitor not found.");
        return monitor;
    }

    private void apply(ProfessionalMonitorDTO dto, ProfessionalMonitor monitor) {
        monitor.setName(dto.name());
        monitor.setBrand(dto.brand());
        monitor.setPrice(dto.price());
        monitor.setScreenSize(dto.screenSize());
        monitor.setPanelType(PanelType.fromId(dto.idPanelType()));
        monitor.setColorAccuracy(dto.colorAccuracy());
        monitor.setHeightAdjustment(dto.heightAdjustment());
    }
}
