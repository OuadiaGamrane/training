package com.octo.formation.service;

import com.octo.formation.domain.Compte;
import com.octo.formation.domain.Virement;
import com.octo.formation.dto.VirementDto;
import com.octo.formation.exceptions.SoldeDisponibleInsuffisantException;
import com.octo.formation.repository.CompteRepository;
import com.octo.formation.repository.VirementRepository;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VirementService {

  private final CompteRepository compteRepository;
  private final VirementRepository virementRepository;

  @Autowired
  public VirementService(CompteRepository compteRepository,
      VirementRepository virementRepository) {
    this.compteRepository = compteRepository;
    this.virementRepository = virementRepository;
  }

  public void virement(VirementDto virementDto) throws SoldeDisponibleInsuffisantException {
    Compte compteEmetteur = compteRepository.findByNrCompte(virementDto.getNrCompteEmetteur());
    Compte compteBeneficiaire = compteRepository
        .findByNrCompte(virementDto.getNrCompteBeneficiaire());

    if (compteEmetteur.getSolde().compareTo(virementDto.getMontantVirement()) < 0) {
      throw new SoldeDisponibleInsuffisantException(
          "Solde insuffisant pour l'utilisateur " + compteEmetteur.getUtilisateur().getUsername());
    }

    Virement virement = new Virement();
    virement.setDateExecution(new Date());
    virement.setCompteBeneficiaire(compteBeneficiaire);
    virement.setCompteEmetteur(compteEmetteur);
    virement.setMontantVirement(virementDto.getMontantVirement());

    virementRepository.save(virement);
  }
}
