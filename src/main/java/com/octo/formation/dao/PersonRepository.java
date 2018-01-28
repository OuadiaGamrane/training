package com.octo.formation.dao;

import com.octo.formation.domaine.Personne;

import java.util.Optional;
import java.util.Set;

public interface PersonRepository {
    Optional<Personne> findOne(long id);

    Set<Personne> findAll();

    void save(Personne personne);

    void delete(Personne personne);
}
