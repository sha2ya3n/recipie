package sha2ya3n.the2gen3tel4man.recepie.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sha2ya3n.the2gen3tel4man.recepie.commands.IngredientCommand;
import sha2ya3n.the2gen3tel4man.recepie.commands.UnitOfMeasureCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Ingredient;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
import sha2ya3n.the2gen3tel4man.recepie.services.IngredientService;
import sha2ya3n.the2gen3tel4man.recepie.services.RecipieService;
import sha2ya3n.the2gen3tel4man.recepie.services.UnitOfMeasureService;

@Slf4j
@Controller
public class IngredientController {

    private final RecipieService recipieService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipieService recipieService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipieService = recipieService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredients")
    public String showIngredients(@PathVariable String recipeId, Model model){
        model.addAttribute("recipe", recipieService.findCommandById(Long.valueOf(recipeId)));
        log.debug("This is from showIngredient method in ingredient controller");

        return "ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String viewIngredients(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.getRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        log.debug("This is from viewIngredient method from ingredient controller that runs now");

        return "ingredient/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId,
                                   @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient", ingredientService.getRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientId)));
        model.addAttribute("uomList", unitOfMeasureService.listOfuom());

        return "ingredient/ingredientform";
    }

    @GetMapping
    public String saveIngredient(@ModelAttribute IngredientCommand command){
        IngredientCommand  savedCommand = ingredientService.saveOrupdateIngredient(command);
        log.debug("This is from saveIgredient Method in ingredient Controller");

        return "/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId,@PathVariable String ingredientId){
        ingredientService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientId));
        log.debug("This from deleteIngredient method in INGREDIENT CONTROLLER");
        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @GetMapping
    @RequestMapping("recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model){
        Recipie recipe = recipieService.findById(Long.valueOf(recipeId));
        // todo return recipe just in case if nothing founded!

        IngredientCommand command = new IngredientCommand();
        command.setRecipeId(Long.valueOf(recipeId));
        model.addAttribute("ingredient", command);
        command.setUnitOfMeasureCommand(new UnitOfMeasureCommand());
        model.addAttribute("uom", unitOfMeasureService.listOfuom());

//        ingredientService.createIngredient(Long.valueOf(recipeId), command);
//        log.debug("This is from new Ingredient method in Ingredient Controller");
        return "ingredient/ingredientform";
    }
}
