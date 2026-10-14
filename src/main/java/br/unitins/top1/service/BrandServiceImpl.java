package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Brand;
import br.unitins.top1.repository.BrandRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class BrandServiceImpl implements BrandService {

    @Inject
    BrandRepository repository;

    @Override
    @Transactional
    public Brand create(Brand brand) {
        if (repository.existsByName(brand.getName())) {
            throw new BadRequestException("Brand with name '" + brand.getName() + "' already exists.");
        }
        repository.persist(brand);
        return brand;
    }

    @Override
    @Transactional
    public void update(Long id, Brand brand) {
        Brand existing = repository.findById(id);
        if (existing == null) {
            throw new NotFoundException("Brand not found.");
        }
        if (repository.existsByNameAndIdNot(brand.getName(), id)) {
            throw new BadRequestException("Brand with name '" + brand.getName() + "' already exists.");
        }
        existing.setName(brand.getName());
        existing.setCountry(brand.getCountry());
        existing.setWebsite(brand.getWebsite());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Brand not found.");
        }
    }

    @Override
    public Brand findById(Long id) {
        Brand brand = repository.findById(id);
        if (brand == null) {
            throw new NotFoundException("Brand not found.");
        }
        return brand;
    }

    @Override
    public List<Brand> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Brand> findAll() {
        return repository.listAll();
    }
}
