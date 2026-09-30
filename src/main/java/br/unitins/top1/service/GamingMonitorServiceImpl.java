package br.unitins.top1.service;

import br.unitins.top1.dto.GamingMonitorDTO;
import br.unitins.top1.dto.GamingMonitorResponseDTO;
import br.unitins.top1.model.GamingMonitor;
import br.unitins.top1.repository.GamingMonitorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class GamingMonitorServiceImpl implements GamingMonitorService {

    @Inject
    GamingMonitorRepository repository;

    @Override
    @Transactional
    public GamingMonitorResponseDTO insert(GamingMonitorDTO dto) {
        GamingMonitor monitor = new GamingMonitor();
        monitor.setName(dto.name());
        monitor.setBrand(dto.brand());
        monitor.setPrice(dto.price());
        monitor.setScreenSize(dto.screenSize());
        monitor.setRefreshRate(dto.refreshRate());
        monitor.setResponseTime(dto.responseTime());
        monitor.setGsyncSupport(dto.gsyncSupport());

        repository.persist(monitor);
        return new GamingMonitorResponseDTO(monitor);
    }

    @Override
    @Transactional
    public GamingMonitorResponseDTO update(Long id, GamingMonitorDTO dto) {
        GamingMonitor monitor = repository.findById(id);
        if (monitor == null) {
            throw new NotFoundException("Monitor não encontrado.");
        }

        monitor.setName(dto.name());
        monitor.setBrand(dto.brand());
        monitor.setPrice(dto.price());
        monitor.setScreenSize(dto.screenSize());
        monitor.setRefreshRate(dto.refreshRate());
        monitor.setResponseTime(dto.responseTime());
        monitor.setGsyncSupport(dto.gsyncSupport());

        return new GamingMonitorResponseDTO(monitor);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Monitor não encontrado.");
        }
    }

    @Override
    public GamingMonitorResponseDTO findById(Long id) {
        GamingMonitor monitor = repository.findById(id);
        if (monitor == null) {
            throw new NotFoundException("Monitor não encontrado.");
        }
        return new GamingMonitorResponseDTO(monitor);
    }

    @Override
    public List<GamingMonitorResponseDTO> findAll() {
        return repository.listAll().stream()
                .map(monitor -> new GamingMonitorResponseDTO(monitor))
                .collect(Collectors.toList());
    }
}