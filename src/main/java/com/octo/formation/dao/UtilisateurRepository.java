package com.octo.formation.dao;

import com.octo.formation.domain.Utilisateur;
import java.util.Optional;
import java.util.Set;

public interface UtilisateurRepository {
    Optional<Utilisateur> findOne(long id);

    Set<Utilisateur> findAll();

    void save(Utilisateur personne);

    void delete(Utilisateur personne);
}
