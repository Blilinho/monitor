package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Feature;
import br.unitins.top1.repository.FeatureRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FeatureServiceImpl implements FeatureService {

    @Inject
    FeatureRepository repository;

    @Override
    @Transactional
    public Feature create(Feature feature) {
        if (repository.existsByName(feature.getName())) {
            throw new BadRequestException("Feature with name '" + feature.getName() + "' already exists.");
        }
        repository.persist(feature);
        return feature;
    }

    @Override
    @Transactional
    public void update(Long id, Feature feature) {
        Feature existing = repository.findById(id);
        if (existing == null) {
            throw new NotFoundException("Feature not found.");
        }
        if (repository.existsByNameAndIdNot(feature.getName(), id)) {
            throw new BadRequestException("Feature with name '" + feature.getName() + "' already exists.");
        }
        existing.setName(feature.getName());
        existing.setDescription(feature.getDescription());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Feature not found.");
        }
    }

    @Override
    public Feature findById(Long id) {
        Feature feature = repository.findById(id);
        if (feature == null) {
            throw new NotFoundException("Feature not found.");
        }
        return feature;
    }

    @Override
    public List<Feature> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Feature> findAll() {
        return repository.listAll();
    }
}
