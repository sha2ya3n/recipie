package sha2ya3n.the2gen3tel4man.recepie.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import sha2ya3n.the2gen3tel4man.recepie.commands.IngredientCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Ingredient;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if(source == null){
            return null;
        }

        final IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(source.getId());
        if(source.getRecipie() != null){
            ingredientCommand.setRecipeId(source.getRecipie().getId());
        }
        ingredientCommand.setDescription(source.getDescription());
        ingredientCommand.setAmount(source.getAmount());
        ingredientCommand.setUnitOfMeasureCommand(uomConverter.convert(source.getUnitOfMeasure()));

        return ingredientCommand;
    }
}
