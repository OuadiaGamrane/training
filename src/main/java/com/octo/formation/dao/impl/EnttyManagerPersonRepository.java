package com.octo.formation.dao.impl;

import com.octo.formation.dao.PersonRepository;
import com.octo.formation.domaine.Personne;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Repository
@Transactional
public class EnttyManagerPersonRepository implements PersonRepository {

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Override
    public Optional<Personne> findOne(long id) {
        return Optional.ofNullable(entityManager.find(Personne.class, id));
    }

    @Override
    public Set<Personne> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Personne> query = criteriaBuilder.createQuery(Personne.class);
        Root<Personne> rootEntry = query.from(Personne.class);
        CriteriaQuery<Personne> all = query.select(rootEntry);
        return new HashSet<>(entityManager.createQuery(all).getResultList());
    }

    @Override
    public void save(Personne personne) {
        entityManager.merge(personne);
    }

    @Override
    public void delete(Personne personne) {
        entityManager.remove(personne);
    }
}
