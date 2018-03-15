package com.octo.formation.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.octo.formation.domain.Compte;
import com.octo.formation.domain.Utilisateur;
import com.octo.formation.domain.Virement;
import com.octo.formation.dto.VirementDto;
import com.octo.formation.exceptions.SoldeDisponibleInsuffisantException;
import com.octo.formation.repository.CompteRepository;
import com.octo.formation.repository.VirementRepository;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class VirementServiceTest {

  @InjectMocks
  VirementService virementService;

  @Mock
  CompteRepository compteRepository;

  @Mock
  VirementRepository virementRepository;

  @Mock
  AutiService autiService;

  @Test
  public void virementSuccess() throws SoldeDisponibleInsuffisantException {
  }

  @Test(expected = SoldeDisponibleInsuffisantException.class)
  public void virementFail() {
  }
}