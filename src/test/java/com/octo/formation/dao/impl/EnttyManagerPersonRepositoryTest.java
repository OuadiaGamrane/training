package com.octo.formation.dao.impl;

import com.octo.formation.config.JpaConfig;
import com.octo.formation.dao.PersonRepository;
import com.octo.formation.domaine.Personne;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EnttyManagerPersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @PersistenceContext(unitName = "entityManager")
    private EntityManager entityManager;

    @Test
    public void findOne() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void save() {
        // Given
        Personne personne = new Personne();
        personne.setNom("Oga");
        personne.setPrenom("Oga1");

        // when
        personRepository.save(personne);

        // then
        assertTrue(countRowsInTableMessage() == 1);
    }

    @Test
    public void delete() {
    }


    private int countRowsInTableMessage() {
        return DataAccessUtils.intResult(entityManager.createNativeQuery("select count(*) from Personne").getResultList());
    }

    private List<Personne> queryAllMessagesFromDatabase() {
        return entityManager.createQuery("from Personne", Personne.class).getResultList();
    }
}