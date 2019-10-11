package sha2ya3n.the2gen3tel4man.recepie.services;

import sha2ya3n.the2gen3tel4man.recepie.commands.RecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;

import java.util.Set;

public interface RecipieService {

    Set<Recipie> getRecipies();

    Recipie findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);

    RecipeCommand findCommandById(Long id);

    void deleteRecipeCommandByObject(Long id);

    void deleteRecipieById(Long id);
}
