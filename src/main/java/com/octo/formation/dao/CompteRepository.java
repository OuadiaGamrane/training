package com.octo.formation.dao;

import com.octo.formation.domain.Compte;
import java.util.Optional;
import java.util.Set;

public interface CompteRepository {
    Optional<Compte> findOne(long id);

    Set<Compte> findAll();

    void save(Compte compte);

    void delete(Compte compte);
}
