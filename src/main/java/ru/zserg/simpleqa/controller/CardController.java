package ru.zserg.simpleqa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.zserg.simpleqa.model.Card;
import ru.zserg.simpleqa.service.CardService;

@Controller
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping(value = "/cards")
    public String getRandomCard(Model model){
        Card card = cardService.getRandomCard();
        model.addAttribute("front", card.getFront());
        model.addAttribute("id", card.getId());
        return "card";
    }

    @GetMapping(value = "/cards/{id}")
    public String getRandomCard(@PathVariable int id,  Model model){
        Card card = cardService.getCard(id);
        model.addAttribute("front", card.getFront());
        model.addAttribute("back", card.getBack());
        model.addAttribute("id", card.getId());
        return "cardFrontBack";
    }
}
