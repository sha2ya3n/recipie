package sha2ya3n.the2gen3tel4man.recepie.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sha2ya3n.the2gen3tel4man.recepie.commands.IngredientCommand;
import sha2ya3n.the2gen3tel4man.recepie.converters.IngredientCommandToIngredient;
import sha2ya3n.the2gen3tel4man.recepie.converters.IngredientToIngredientCommand;
import sha2ya3n.the2gen3tel4man.recepie.converters.RecipeCommandToRecipe;
import sha2ya3n.the2gen3tel4man.recepie.model.Ingredient;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
import sha2ya3n.the2gen3tel4man.recepie.repository.RecipieRepository;
import sha2ya3n.the2gen3tel4man.recepie.repository.UnitOfMeasureRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipieRepository recipieRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;
    private final RecipeCommandToRecipe recipeConverter;
    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientServiceImpl(RecipieRepository recipieRepository, IngredientToIngredientCommand ingredientConverter,
                                 RecipeCommandToRecipe recipeConverter, IngredientCommandToIngredient ingredientCommandToIngredient,
                                 UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureService unitOfMeasureService) {
        this.recipieRepository = recipieRepository;
        this.ingredientToIngredientCommand = ingredientConverter;
        this.recipeConverter = recipeConverter;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public IngredientCommand getRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
        Optional<Recipie> recipieOptional = recipieRepository.findById(recipeId);
        if(!recipieOptional.isPresent()){
            throw new RuntimeException("This is RuntimeException from method in ingredientServiceImpl");
        }
        Recipie recipie = recipieOptional.get();

        Optional<IngredientCommand> ingredientCommandOptional = recipie.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientToIngredientCommand.convert(ingredient)).findFirst();

        if(!ingredientCommandOptional.isPresent()){
            throw new RuntimeException("This is RuntimeException from method in ingredientServiceImpl (from second)");
        }
        return ingredientCommandOptional.get();
    }

    @Override
    public IngredientCommand saveOrupdateIngredient(IngredientCommand command){
        Optional<Recipie> recipieOptional = recipieRepository.findById(command.getId());
        if(!recipieOptional.isPresent()){
            log.error("Somthing wrong in saveOrUpdateIngredient method");
            return new IngredientCommand();
//            throw new RuntimeException("This is RunTimeException from method in ingredientServiceImpl (from third)");
        }
        Recipie recipie = recipieOptional.get();

        Optional<Ingredient> optionalIngredient = recipie.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId())).findFirst();

        if(optionalIngredient.isPresent()){
            Ingredient foundIngredient = optionalIngredient.get();
            foundIngredient.setId(command.getId());
            foundIngredient.setDescription(command.getDescription());
            foundIngredient.setAmount(command.getAmount());
            foundIngredient.setUnitOfMeasure(unitOfMeasureRepository
                    .findById(command.getUnitOfMeasureCommand().getId()).orElseThrow(()-> new RuntimeException("UOM NOT FOUND")));
        }else{

            Ingredient newIngredient = ingredientCommandToIngredient.convert(command);
            newIngredient.setRecipie(recipie);
            recipie.addIngredient(newIngredient);

//            recipie.addIngredient(ingredientCommandToIngredient.convert(command));
        }

        Recipie savedRecipe = recipieRepository.save(recipie);
        Optional<Ingredient> savedIngredientOptional = savedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();
        if(!savedIngredientOptional.isPresent()){
            savedIngredientOptional = savedRecipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getAmount().equals(command.getAmount()))
                    .filter(ingredient -> ingredient.getDescription().equals(command.getDescription()))
                    .filter(ingredient -> ingredient.getUnitOfMeasure().getId().equals(command.getId()))
                    .findFirst();
        }
        return ingredientToIngredientCommand.convert(savedIngredientOptional.get());

//        return ingredientToIngredientCommand.convert(savedRecipe.getIngredients().stream()
//        .filter(recipeIngredient -> recipeIngredient.getId().equals(command.getId()))
//        .findFirst()
//        .get());

    }

    @Override
    public void deleteById(Long recipeId, Long ingredientId) {
        Optional<Recipie> optionalRecipie = recipieRepository.findById(recipeId);

        if(optionalRecipie.isPresent()){
            Recipie recipe = optionalRecipie.get();
            Optional<Ingredient> optionalIngredient = recipe.getIngredients().stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if(optionalIngredient.isPresent()){
                log.debug("Ingredient found");
                Ingredient deleteIngredient = optionalIngredient.get();
                deleteIngredient.setRecipie(null);
                recipe.getIngredients().remove(optionalIngredient.get());
                recipieRepository.save(recipe);
            }
        }else{
            log.error("RECIPE IS NOT FOUND IN REPOSITORY");
        }
    }

    @Override
    public void createIngredient(Long recipeId, IngredientCommand command) {
        Optional<Recipie> optionalRecipie = recipieRepository.findById(recipeId);
        if (optionalRecipie.isPresent()) {
            Recipie recipe = optionalRecipie.get();
            Set<Ingredient> ingredientSet = new HashSet<>();
            ingredientSet.add(ingredientCommandToIngredient.convert(command));
            recipe.setIngredients(ingredientSet);
            recipieRepository.save(recipe);
        }else{
            log.error("RECIPE NOT FOUND IN REPOSITORY");
        }
    }
}
