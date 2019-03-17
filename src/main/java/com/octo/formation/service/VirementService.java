package com.octo.formation.service;

import com.octo.formation.domain.Compte;
import com.octo.formation.domain.Virement;
import com.octo.formation.domain.util.EventType;
import com.octo.formation.dto.VirementDto;
import com.octo.formation.exceptions.CompteNonExistantException;
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
  private final AutiService autiService;

  @Autowired
  public VirementService(CompteRepository compteRepository,
      VirementRepository virementRepository,
      AutiService autiService) {
    this.compteRepository = compteRepository;
    this.virementRepository = virementRepository;
    this.autiService = autiService;
  }

  public void virement(VirementDto virementDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException {
    Compte compteEmetteur = compteRepository.findByNrCompte(virementDto.getNrCompteEmetteur());
    Compte compteBeneficiaire = compteRepository
        .findByNrCompte(virementDto.getNrCompteBeneficiaire());

    if(compteBeneficiaire == null || compteEmetteur == null) {
      throw new CompteNonExistantException("Cannot find account");
    }

    if (compteEmetteur.getSolde().compareTo(virementDto.getMontantVirement()) < 0) {
      throw new SoldeDisponibleInsuffisantException(
          "Solde insuffisant pour l'utilisateur " + compteEmetteur.getUtilisateur().getUsername());
    }

    compteEmetteur.setSolde(compteEmetteur.getSolde().subtract(virementDto.getMontantVirement()));
    compteRepository.save(compteEmetteur);

    compteBeneficiaire
        .setSolde(compteBeneficiaire.getSolde().add(virementDto.getMontantVirement()));
    compteRepository.save(compteBeneficiaire);

    Virement virement = new Virement();
    virement.setDateExecution(virementDto.getDate());
    virement.setCompteBeneficiaire(compteBeneficiaire);
    virement.setCompteEmetteur(compteEmetteur);
    virement.setMontantVirement(virementDto.getMontantVirement());

    virementRepository.save(virement);

    autiService.audit(EventType.VIREMENT,
        "Virement depuis " + virementDto.getNrCompteEmetteur() + " vers " + virementDto
            .getNrCompteBeneficiaire() + " d'un montant de " + virementDto.getMontantVirement()
            .toString());
  }
}
