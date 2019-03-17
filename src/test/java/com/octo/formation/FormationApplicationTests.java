package com.octo.formation;

import com.octo.formation.dao.UtilisateurRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FormationApplicationTests {

	@Autowired
	UtilisateurRepository utilisateurRepository;

	@PersistenceContext(unitName = "entityManager")
	private EntityManager entityManager;

	@Test
	@Transactional
	public void contextLoads_testpersistence() {

		// create User

		// save user with repository

		// print user

		// set some attribute

		// entityManager.flush

		// fetch same user from database and print it
	}


	@Test
	@Transactional
	public void contextLoads_testpersistence_detach() {
		// create User

		// save user with repository

		// print user

		// set some attribute

		// detach user object

		// entityManager.flush

		// fetch same user from database and print it
	}

}
