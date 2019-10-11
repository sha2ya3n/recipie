package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.RecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipie> {

    private final NoteCommandToNote noteConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(NoteCommandToNote noteConverter,
                                 IngredientCommandToIngredient ingredientConverter, CategoryCommandToCategory categoryConverter) {
        this.noteConverter = noteConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipie convert(RecipeCommand source) {
        if(source == null){
            return null;
        }

        final Recipie recipe = new Recipie();
        recipe.setId(source.getId());
        recipe.setDescription(source.getDescription());
        recipe.setRating(source.getRating());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setCookTime(source.getCookTime());
        recipe.setNote(noteConverter.convert(source.getNoteCommand()));

        if(source.getCategoryCommandSet() != null && source.getCategoryCommandSet().size() >0){
            source.getCategoryCommandSet().forEach(category ->{
                recipe.getCategory().add(categoryConverter.convert(category));
            });
        }

        if(source.getIngredientCommandSe() != null && source.getIngredientCommandSe().size() >0){
            source.getIngredientCommandSe().forEach(ingredient -> {
                recipe.getIngredients().add(ingredientConverter.convert(ingredient));
            });
        }

        return recipe;

    }
}
