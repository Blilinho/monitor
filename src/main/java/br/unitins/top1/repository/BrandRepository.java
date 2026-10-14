package br.unitins.top1.repository;

import java.util.List;

import br.unitins.top1.model.Brand;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BrandRepository implements PanacheRepository<Brand> {

    public List<Brand> findByName(String name) {
        return find("upper(name) LIKE upper(?1)", "%" + name + "%").list();
    }

    public boolean existsByName(String name) {
        return count("upper(name) = upper(?1)", name) > 0;
    }

    public boolean existsByNameAndIdNot(String name, Long id) {
        return count("upper(name) = upper(?1) and id != ?2", name, id) > 0;
    }
}
