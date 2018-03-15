package com.octo.formation.dao.impl;

import com.octo.formation.dao.CompteRepository;
import com.octo.formation.domain.Compte;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EntityManagerCompteRepository implements CompteRepository {

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Override
    public Optional<Compte> findOne(long id) {
        return Optional.ofNullable(entityManager.find(Compte.class, id));
    }

    @Override
    public Set<Compte> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Compte> query = criteriaBuilder.createQuery(Compte.class);
        Root<Compte> rootEntry = query.from(Compte.class);
        CriteriaQuery<Compte> all = query.select(rootEntry);
        return new HashSet<>(entityManager.createQuery(all).getResultList());
    }

    @Override
    public void save(Compte compte) {
        entityManager.persist(compte);
        entityManager.flush();
    }

    @Override
    public void delete(Compte compte) {
        entityManager.remove(compte);
        entityManager.flush();
    }
}
