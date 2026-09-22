package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.Monitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitorRepository implements PanacheRepository<Monitor> {
    public List<Monitor> findByName(String nome) {
        return find("upper(nome) LIKE upper(?1)", "%" + nome + "%").list();
    }

    public List<Monitor> findByBrand(String marca) {
        return find("upper(brand) LIKE upper(?1)", "%" + marca + "%").list();
    }
}