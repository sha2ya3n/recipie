package sha2ya3n.the2gen3tel4man.recepie.services;

import sha2ya3n.the2gen3tel4man.recepie.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand getRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveOrupdateIngredient(IngredientCommand ingredientCommand);

    void deleteById(Long recipeId, Long ingredientId);

    void createIngredient(Long recipeId, IngredientCommand command);
}
