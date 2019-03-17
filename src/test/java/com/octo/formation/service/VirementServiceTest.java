package com.octo.formation.service;

import com.octo.formation.exceptions.CompteNonExistantException;
import com.octo.formation.exceptions.SoldeDisponibleInsuffisantException;
import com.octo.formation.repository.CompteRepository;
import com.octo.formation.repository.VirementRepository;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VirementServiceTest {
  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Mock
  CompteRepository compteRepository;

  @Mock
  VirementRepository virementRepository;

  @Mock
  AutiService autiService;

  @InjectMocks
  VirementService virementService;


  @Test
  public void virementSuccess() throws SoldeDisponibleInsuffisantException, CompteNonExistantException {


  }

  @Test
  public void virementFail_soldInsuffisant() {
  }

  @Test
  public void virementFail_compteBeneficiaireNonExistant() {
  }
}