package com.octo.formation.dao.impl;

import com.octo.formation.dao.UtilisateurRepository;
import com.octo.formation.domain.Utilisateur;
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
public class EntityManagerUtilisateurRepository implements UtilisateurRepository {

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Override
    public Optional<Utilisateur> findOne(long id) {
        return Optional.ofNullable(entityManager.find(Utilisateur.class, id));
    }

    @Override
    public Set<Utilisateur> findAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Utilisateur> query = criteriaBuilder.createQuery(Utilisateur.class);
        Root<Utilisateur> rootEntry = query.from(Utilisateur.class);
        CriteriaQuery<Utilisateur> all = query.select(rootEntry);
        return new HashSet<>(entityManager.createQuery(all).getResultList());
    }

    @Override
    public void save(Utilisateur utilisateur) {
        entityManager.persist(utilisateur);
        entityManager.flush();
    }

    @Override
    public void delete(Utilisateur utilisateur) {
        entityManager.remove(utilisateur);
        entityManager.flush();
    }
}
