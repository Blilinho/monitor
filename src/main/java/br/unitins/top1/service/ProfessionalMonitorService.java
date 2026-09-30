package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.dto.ProfessionalMonitorDTO;
import br.unitins.top1.dto.ProfessionalMonitorResponseDTO;

public interface ProfessionalMonitorService {
    ProfessionalMonitorResponseDTO create(ProfessionalMonitorDTO dto);
    ProfessionalMonitorResponseDTO update(Long id, ProfessionalMonitorDTO dto);
    void delete(Long id);
    ProfessionalMonitorResponseDTO findById(Long id);
    List<ProfessionalMonitorResponseDTO> findAll();
}
