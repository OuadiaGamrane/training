package com.octo.formation.dao.impl;

import static org.junit.Assert.assertTrue;

import com.octo.formation.domain.Utilisateur;
import com.octo.formation.repository.UtilisateurRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EntityManagerUtilisateurRepositoryTest {

  @Autowired
  private UtilisateurRepository personRepository;

  @Test
  public void findOne() {
  }

  @Test
  public void findAll() {
  }

  @Test
  public void save() {
    // Given
    Utilisateur personne = new Utilisateur();
    personne.setUsername("Oga");
    personne.setLastname("Oga1");

    // when
    personRepository.save(personne);

    // then
    assertTrue(personRepository.count() == 1);
  }

  @Test
  public void delete() {
  }
}