package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.Monitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MonitorRepository implements PanacheRepository<Monitor> {
    public List<Monitor> findByNome(String nome) {
        return find("nome LIKE ?1", "%" + nome + "%").list();
    }
}