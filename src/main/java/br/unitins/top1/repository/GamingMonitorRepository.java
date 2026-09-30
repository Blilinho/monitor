package br.unitins.top1.repository;

import br.unitins.top1.model.GamingMonitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GamingMonitorRepository implements PanacheRepository<GamingMonitor> {
    
}