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

    // given
    Utilisateur utilisateur1 = new Utilisateur();
    utilisateur1.setUsername("user1");
    utilisateur1.setLastname("last1");
    utilisateur1.setFirstname("first1");
    utilisateur1.setGender("Male");

    Utilisateur utilisateur2 = new Utilisateur();
    utilisateur2.setUsername("user2");
    utilisateur2.setLastname("last2");
    utilisateur2.setFirstname("first2");
    utilisateur2.setGender("Female");

    Compte compte1 = new Compte();
    compte1.setNrCompte("010000A000001000");
    compte1.setRib("RIB1");
    compte1.setSolde(BigDecimal.valueOf(200000L));
    compte1.setUtilisateur(utilisateur1);

    Compte compte2 = new Compte();
    compte2.setNrCompte("010000B025001000");
    compte2.setRib("RIB2");
    compte2.setSolde(BigDecimal.valueOf(140000L));
    compte2.setUtilisateur(utilisateur2);

    VirementDto virementDto = new VirementDto();
    virementDto.setMontantVirement(BigDecimal.valueOf(200L));
    virementDto.setNrCompteEmetteur("010000A000001000");
    virementDto.setNrCompteBeneficiaire("010000B025001000");

    when(compteRepository.findByNrCompte("010000A000001000")).thenReturn(compte1);
    when(compteRepository.findByNrCompte("010000B025001000")).thenReturn(compte2);

    // when
    virementService.virement(virementDto);

    //then
    ArgumentCaptor<Virement> virement = ArgumentCaptor.forClass(Virement.class);
    Mockito.verify(virementRepository).save(virement.capture());
    Assert.assertEquals("010000A000001000", virement.getValue().getCompteEmetteur().getNrCompte());

    Mockito.verify(compteRepository, times(2)).save(any(Compte.class));
  }

  @Test(expected = SoldeDisponibleInsuffisantException.class)
  public void virementFail() throws SoldeDisponibleInsuffisantException {
    // given
    Utilisateur utilisateur1 = new Utilisateur();
    utilisateur1.setUsername("user1");
    utilisateur1.setLastname("last1");
    utilisateur1.setFirstname("first1");
    utilisateur1.setGender("Male");

    Utilisateur utilisateur2 = new Utilisateur();
    utilisateur2.setUsername("user2");
    utilisateur2.setLastname("last2");
    utilisateur2.setFirstname("first2");
    utilisateur2.setGender("Female");

    Compte compte1 = new Compte();
    compte1.setNrCompte("010000A000001000");
    compte1.setRib("RIB1");
    compte1.setSolde(BigDecimal.valueOf(200000L));
    compte1.setUtilisateur(utilisateur1);

    Compte compte2 = new Compte();
    compte2.setNrCompte("010000B025001000");
    compte2.setRib("RIB2");
    compte2.setSolde(BigDecimal.valueOf(140000L));
    compte2.setUtilisateur(utilisateur2);

    VirementDto virementDto = new VirementDto();
    virementDto.setMontantVirement(BigDecimal.valueOf(200000000000000000L));
    virementDto.setNrCompteEmetteur("010000A000001000");
    virementDto.setNrCompteBeneficiaire("010000B025001000");

    when(compteRepository.findByNrCompte("010000A000001000")).thenReturn(compte1);
    when(compteRepository.findByNrCompte("010000B025001000")).thenReturn(compte2);

    // when
    virementService.virement(virementDto);
  }
}