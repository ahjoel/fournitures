package com.gestock.fourniture.presentation;

import com.gestock.fourniture.model.dto.CommandeDto;
import com.gestock.fourniture.model.dto.LivraisonDto;
import com.gestock.fourniture.service.CommandeService;
import com.gestock.fourniture.service.LivraisonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class LivraisonController {
    @Autowired
    LivraisonService livraisonService;

    @RequestMapping(path="livraisons", method = RequestMethod.GET)
    public List<LivraisonDto> listeLivraison() {
        return livraisonService.listLivraisons();
    }

    @RequestMapping(value="livraison/addliv", method = RequestMethod.POST)
    public LivraisonDto enregistrerLivraison(@RequestBody LivraisonDto livraisonDto){
        return livraisonService.ajouterLivraison(livraisonDto);
    }

    private LivraisonDto getLivraisonDtoIdExist(Long id) {
        LivraisonDto livraisonDto = LivraisonDto.builder().id(id).build();
        LivraisonDto livraisonDtoFound = livraisonService.getLivraisonById(livraisonDto);

        if (livraisonDtoFound == null) {
            throw new RuntimeException("Code 257 : l'id de la livraison que vous voulez modifier n'existe pas");
        }
        return livraisonDtoFound;
    }

    @RequestMapping(value="livraison/{id}", method = RequestMethod.GET)
    public LivraisonDto afficherLivraison(@PathVariable("id") Long id) {
        return getLivraisonDtoIdExist(id);
    }

    @RequestMapping(value="livraison/delete/{id}", method=RequestMethod.DELETE)
    public Boolean supprimerLivraison(@PathVariable("id") Long id) {
        return livraisonService.supprimerLivraison(id);
    }

}
