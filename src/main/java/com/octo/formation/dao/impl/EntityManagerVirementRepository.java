package com.octo.formation.dao.impl;

import com.octo.formation.dao.VirementRepository;
import com.octo.formation.domain.Virement;
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
public class EntityManagerVirementRepository implements VirementRepository {

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Override
    public Optional<Virement> findOne(long id) {
        return Optional.ofNullable(entityManager.find(Virement.class, id));
    }

    @Override
    public Set<Virement> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Virement> query = criteriaBuilder.createQuery(Virement.class);
        Root<Virement> rootEntry = query.from(Virement.class);
        CriteriaQuery<Virement> all = query.select(rootEntry);
        return new HashSet<>(entityManager.createQuery(all).getResultList());
    }

    @Override
    public void save(Virement virement) {
        entityManager.persist(virement);
        entityManager.flush();
    }

    @Override
    public void delete(Virement virement) {
        entityManager.remove(virement);
        entityManager.flush();
    }
}
