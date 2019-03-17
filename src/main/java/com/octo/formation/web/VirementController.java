package com.octo.formation.web;

import com.octo.formation.dto.VirementDto;
import com.octo.formation.exceptions.CompteNonExistantException;
import com.octo.formation.exceptions.SoldeDisponibleInsuffisantException;
import com.octo.formation.mapper.VirementMapper;
import com.octo.formation.service.VirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController(value = "/virements")
class VirementController {

  @Autowired
  private VirementService virementService;

  @GetMapping
  List<VirementDto> loadAll() {
    return Optional.ofNullable(virementService.loadAll()).orElse(Collections.emptyList())
            .stream().map(VirementMapper::map).collect(Collectors.toList());
  }

  @PostMapping("/virements")
  @ResponseStatus(HttpStatus.CREATED)
  public void createTransaction(@RequestBody VirementDto virementDto)
      throws SoldeDisponibleInsuffisantException, CompteNonExistantException {
    virementService.virement(virementDto);
  }
}
