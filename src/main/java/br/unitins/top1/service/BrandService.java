package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Brand;

public interface BrandService {
    Brand create(Brand brand);
    void update(Long id, Brand brand);
    void delete(Long id);
    Brand findById(Long id);
    List<Brand> findByName(String name);
    List<Brand> findAll();
}
