package sha2ya3n.the2gen3tel4man.recepie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sha2ya3n.the2gen3tel4man.recepie.services.RecipieService;


@Controller
public class IndexController {

    private final RecipieService recipieService;


    public IndexController(RecipieService recipieService) {
        this.recipieService = recipieService;
    }

    @RequestMapping({"", "/" , "index", "index.html"})
    public String showRecipies(Model model){
        model.addAttribute("recipes", recipieService.getRecipies());
        return "index";
    }
}
