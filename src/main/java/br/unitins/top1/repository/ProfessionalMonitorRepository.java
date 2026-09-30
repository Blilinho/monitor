package br.unitins.top1.repository;

import br.unitins.top1.model.ProfessionalMonitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessionalMonitorRepository implements PanacheRepository<ProfessionalMonitor> {
}
