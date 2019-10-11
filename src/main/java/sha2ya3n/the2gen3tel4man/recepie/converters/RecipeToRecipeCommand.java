package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.RecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Category;
import sha2ya3n.the2gen3tel4man.recepie.model.Ingredient;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;

@Component
public class RecipeToRecipeCommand implements Converter<Recipie, RecipeCommand> {

    private final NoteToNoteCommand noteConverter;
    private final IngredientToIngredientCommand ingredientConverter;
    private final CategoryToCateGoryCommand categoryConverter;

    public RecipeToRecipeCommand(NoteToNoteCommand noteConverter,
                                 IngredientToIngredientCommand ingredientConverter, CategoryToCateGoryCommand categoryConverter) {
        this.noteConverter = noteConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipie source) {
        if(source == null){
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setDirection(source.getDirection());
        recipeCommand.setId(source.getId());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setImage(source.getImage());
        recipeCommand.setNoteCommand(noteConverter.convert(source.getNote()));

        if(source.getCategory() != null && source.getCategory().size() >0){
            source.getCategory().forEach((Category category) ->{
                recipeCommand.getCategoryCommandSet().add(categoryConverter.convert(category));
            });
        }

        if(source.getIngredients() != null && source.getIngredients().size() >0){
            source.getIngredients().forEach((Ingredient ingredient) ->{
                recipeCommand.getIngredientCommandSe().add(ingredientConverter.convert(ingredient));
            });
        }

        return recipeCommand;

    }
}
