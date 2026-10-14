package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.GamingMonitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GamingMonitorRepository implements PanacheRepository<GamingMonitor> {

    public List<GamingMonitor> findByName(String name) {
        return find("upper(name) LIKE upper(?1)", "%" + name + "%").list();
    }
}
