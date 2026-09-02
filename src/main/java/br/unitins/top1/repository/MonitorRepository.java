package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.Monitor;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;

@ApplicationScoped
@RequestScoped
@SessionScoped
public class MonitorRepository implements PanacheRepository<Monitor> {
    public List<Monitor> findByNome(String nome) {
        return find("nome LIKE ?", "%" + nome + "%").list();
    }
}