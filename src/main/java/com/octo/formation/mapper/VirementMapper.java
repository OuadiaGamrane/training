package com.octo.formation.mapper;

import com.octo.formation.domain.Virement;
import com.octo.formation.dto.VirementDto;

public class VirementMapper {

    public static VirementDto map(Virement virement) {
        if (virement == null) {
            return null;
        }
        VirementDto virementDto = new VirementDto();
        virementDto.setMontantVirement(virement.getMontantVirement());
        virementDto.setNrCompteBeneficiaire(virement.getCompteBeneficiaire().getNrCompte());
        virementDto.setNrCompteEmetteur(virement.getCompteEmetteur().getNrCompte());
        virementDto.setDate(virement.getDateExecution());
        virementDto.setMotif(virement.getMotifVirement());

        return virementDto;

    }
}
