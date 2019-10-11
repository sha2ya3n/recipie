package sha2ya3n.the2gen3tel4man.recepie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sha2ya3n.the2gen3tel4man.recepie.commands.RecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
import sha2ya3n.the2gen3tel4man.recepie.services.RecipieService;

@Slf4j
@Controller
public class RecipieController {

    private final RecipieService recipieService;

    public RecipieController(RecipieService recipieService) {
        this.recipieService = recipieService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model) {
        log.debug("This is from method in recipie Controller");
        model.addAttribute("recipe", recipieService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        log.debug("This is from");
        return "recipe/recipeform";
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand recipeCommand = recipieService.saveRecipeCommand(command);
        return "redirect:/recipe/" + recipeCommand.getId()+"/show/";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipieService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeform";
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/delete")
    public String deleteRecipieById(@PathVariable String id){
        recipieService.deleteRecipieById(Long.valueOf(id));
        log.info("Delete method has been called");
        return "redirect:/";
    }

}
