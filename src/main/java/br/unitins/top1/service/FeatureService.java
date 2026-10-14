package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Feature;

public interface FeatureService {
    Feature create(Feature feature);
    void update(Long id, Feature feature);
    void delete(Long id);
    Feature findById(Long id);
    List<Feature> findByName(String name);
    List<Feature> findAll();
}
