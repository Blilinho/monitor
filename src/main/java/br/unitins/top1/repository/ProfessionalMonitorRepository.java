package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.ProfessionalMonitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProfessionalMonitorRepository implements PanacheRepository<ProfessionalMonitor> {

    public List<ProfessionalMonitor> findByName(String name) {
        return find("upper(name) LIKE upper(?1)", "%" + name + "%").list();
    }
}
