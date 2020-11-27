package ru.zserg.simpleqa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zserg.simpleqa.model.Card;
import ru.zserg.simpleqa.service.CardService;

@Controller
public class CardController {

    @Autowired
    CardService cardService;

    @GetMapping(value = "/cards")
    public String getRandomCard(@RequestParam(required = false) Integer id, Model model){
        Card card = (id == null) ? cardService.getRandomCard() : cardService.getCard(id);
        model.addAttribute("front", card.getFront());
        model.addAttribute("back", card.getBack());
        model.addAttribute("id", card.getId());
        model.addAttribute("tag", card.getFileName());
        return "card";
    }

    @GetMapping(value = "/cards/{tag}")
    public String getRandomCard(@RequestParam(required = false) Integer id, @PathVariable String tag, Model model){
        Card card = cardService.getRandomCardFromFile(tag);
        model.addAttribute("front", card.getFront());
        model.addAttribute("back", card.getBack());
        model.addAttribute("id", card.getId());
        model.addAttribute("tag", tag);
        return "cardWithTag";
    }

//    @GetMapping(value = "/cards/{id}")
//    public String getRandomCard(@PathVariable int id,  Model model){
//        Card card = cardService.getCard(id);
//        model.addAttribute("front", card.getFront());
//        model.addAttribute("back", card.getBack());
//        model.addAttribute("id", card.getId());
//        return "cardFrontBack";
//    }
}
