package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Accessory;
import br.unitins.top1.repository.AccessoryRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class AccessoryServiceImpl implements AccessoryService {

    @Inject
    AccessoryRepository repository;

    @Override
    @Transactional
    public Accessory create(Accessory accessory) {
        repository.persist(accessory);
        return accessory;
    }

    @Override
    @Transactional
    public void update(Long id, Accessory accessory) {
        Accessory existing = repository.findById(id);
        if (existing == null) {
            throw new NotFoundException("Accessory not found.");
        }
        existing.setName(accessory.getName());
        existing.setCategory(accessory.getCategory());
        existing.setPrice(accessory.getPrice());
        existing.setDescription(accessory.getDescription());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Accessory not found.");
        }
    }

    @Override
    public Accessory findById(Long id) {
        Accessory accessory = repository.findById(id);
        if (accessory == null) {
            throw new NotFoundException("Accessory not found.");
        }
        return accessory;
    }

    @Override
    public List<Accessory> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Accessory> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    @Override
    public List<Accessory> findAll() {
        return repository.listAll();
    }
}
