package br.unitins.top1.service;

import java.util.List;

import br.unitins.top1.model.Accessory;

public interface AccessoryService {
    Accessory create(Accessory accessory);
    void update(Long id, Accessory accessory);
    void delete(Long id);
    Accessory findById(Long id);
    List<Accessory> findByName(String name);
    List<Accessory> findByCategory(String category);
    List<Accessory> findAll();
}
