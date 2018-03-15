package com.octo.formation.dao;

import com.octo.formation.domain.Virement;
import java.util.Optional;
import java.util.Set;

public interface VirementRepository {
    Optional<Virement> findOne(long id);

    Set<Virement> findAll();

    void save(Virement virement);

    void delete(Virement virement);
}
