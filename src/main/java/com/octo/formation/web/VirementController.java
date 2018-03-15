package com.octo.formation.web;

import com.octo.formation.dto.VirementDto;
import com.octo.formation.exceptions.CompteNonExistantException;
import com.octo.formation.exceptions.SoldeDisponibleInsuffisantException;
import com.octo.formation.service.VirementService;
import java.time.LocalDateTime;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
class VirementController {

  @Autowired
  private VirementService virementService;

  @GetMapping("/virements")
  String load(Model model) {
    model.addAttribute("virements", virementService.loadAll());
    return "views/virements";
  }

  @GetMapping("/virements/new")
  String newVirement(Model model) {
    model.addAttribute("now", LocalDateTime.now());
    model.addAttribute("virementDto", new VirementDto());
    return "views/virementForm";
  }

  @PostMapping("/virements")
  public String submit(@ModelAttribute VirementDto virementDto)
      throws SoldeDisponibleInsuffisantException, CompteNonExistantException {
    virementDto.setDate(new Date());
    virementService.virement(virementDto);
    return "redirect:virements";
  }

  @ExceptionHandler(SoldeDisponibleInsuffisantException.class)
  public String handleSoldeDisponibleInsuffisantException(SoldeDisponibleInsuffisantException ex, Model model) {
    // add needed model attributes
    model.addAttribute("exception",  ex.getMessage());
    return newVirement(model);
  }

  @ExceptionHandler(CompteNonExistantException.class)
  public String handleCompteNonExistantException(CompteNonExistantException ex, Model model) {
    // add needed model attributes
    model.addAttribute("exception",  ex.getMessage());
    return newVirement(model);
  }
}
