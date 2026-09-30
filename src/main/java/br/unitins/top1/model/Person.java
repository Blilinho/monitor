package br.unitins.top1.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person extends DefaultEntity {
    private String name;
    private String cpf;
    private String email;


}
