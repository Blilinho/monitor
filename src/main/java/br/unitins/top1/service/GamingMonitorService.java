package br.unitins.top1.service;

import br.unitins.top1.dto.GamingMonitorDTO;
import br.unitins.top1.dto.GamingMonitorResponseDTO;
import java.util.List;

public interface GamingMonitorService {
    GamingMonitorResponseDTO insert(GamingMonitorDTO dto);
    GamingMonitorResponseDTO update(Long id, GamingMonitorDTO dto);
    void delete(Long id);
    GamingMonitorResponseDTO findById(Long id);
    List<GamingMonitorResponseDTO> findAll();
}