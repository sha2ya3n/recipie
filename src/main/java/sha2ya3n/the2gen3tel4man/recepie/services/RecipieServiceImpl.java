package sha2ya3n.the2gen3tel4man.recepie.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sha2ya3n.the2gen3tel4man.recepie.commands.RecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.converters.RecipeCommandToRecipe;
import sha2ya3n.the2gen3tel4man.recepie.converters.RecipeToRecipeCommand;
import sha2ya3n.the2gen3tel4man.recepie.model.Recipie;
import sha2ya3n.the2gen3tel4man.recepie.repository.RecipieRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipieServiceImpl implements RecipieService{

    private final RecipieRepository recipieRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipieServiceImpl(RecipieRepository recipieRepository, RecipeCommandToRecipe recipeCommandToRecipe,
                              RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipieRepository = recipieRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipie> getRecipies() {
        log.debug("This is from Recipie Service implementation");
        Set<Recipie> recipieSet = new HashSet<>();
        recipieRepository.findAll().iterator().forEachRemaining(recipieSet::add);
        return recipieSet;
    }

    @Override
    public Recipie findById(Long id) {
        Optional<Recipie> optionalRecipie = recipieRepository.findById(id);
       if(!optionalRecipie.isPresent()){
           throw new RuntimeException("RuntimeException from find by id in RecipieServiceImpl");
       }
       return optionalRecipie.get();
    }

    // this service is for when the form has been submit, and post for backend and to save in DB
    // this method catch it and convert it to a new Recipe if it dose not exist and if it dose, it merge!
    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipie detachedRecipe = recipeCommandToRecipe.convert(command);
        Recipie savedRecipe = recipieRepository.save(detachedRecipe);
        log.debug("This is from saveRecipeCommand Service that saved: " + savedRecipe.getId());

        return recipeToRecipeCommand.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
    }

    @Override
    @Transactional
    public void deleteRecipeCommandByObject(Long id) {
        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(findById(id));
        Recipie deleteRecipe = recipeCommandToRecipe.convert(recipeCommand);
        recipieRepository.delete(deleteRecipe);
    }

    @Override
    public void deleteRecipieById(Long id) {
        recipieRepository.deleteById(id);
    }
}
